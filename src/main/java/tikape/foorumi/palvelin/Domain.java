/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.foorumi.palvelin;

import java.sql.SQLException;
import java.util.List;
import tikape.foorumi.database.Database;
import tikape.foorumi.database.dao.*;
import tikape.foorumi.domain.Alue;
import tikape.foorumi.domain.Keskustelu;
import tikape.foorumi.domain.Viesti;

/**
 *
 * @author jemisalo
 */
public class Domain {

    private final AlueDao alueDao;
    private final KeskusteluDao keskusteluDao;
    private final ViestiDao viestiDao;

    public Domain(Database database) {
        this.alueDao = new AlueDao(database);
        this.keskusteluDao = new KeskusteluDao(database);
        this.viestiDao = new ViestiDao(database);
    }

    public List<Alue> haeAlueet() throws SQLException {
        List<Alue> alueList = alueDao.findAll();
        for (Alue alue : alueList) {
            alue.setKeskusteluLkm(this.keskusteluDao.countAlueella(alue.getId()));
            alue.setViestiLkm(this.viestiDao.countAlueella(alue.getId()));
            Viesti viimeisin = this.viestiDao.alueenViimeisin(alue.getId());
            if(viimeisin!=null){
                alue.setViimeisinNimimerkki(viimeisin.getNimimerkki());
                alue.setViimeisinAika(viimeisin.getLahetysAika());
                alue.setViimeisinKeskusteluId(viimeisin.getKeskustelu());
            }else{
                alue.setViimeisinNimimerkki("");
                alue.setViimeisinAika(null);
                alue.setViimeisinKeskusteluId(-1);
            }
        }
        return alueList;
    }

    public List<Keskustelu> haeKeskustelutAlueella(int alueId) throws SQLException {
        List<Keskustelu> keskList = this.keskusteluDao.getAlueella(alueId);
        for (Keskustelu keskustelu : keskList) {
            keskustelu.setViestiLkm(this.viestiDao.countKeskustelulla(keskustelu.getId()));
            Viesti viimeisin = this.viestiDao.keskustelunViimeisin(keskustelu.getId());
            if(viimeisin!=null){
                keskustelu.setViimeisinNimimerkki(viimeisin.getNimimerkki());
                keskustelu.setViimeisinAika(viimeisin.getLahetysAika());
            }else{
                keskustelu.setViimeisinNimimerkki("");
                keskustelu.setViimeisinAika(null);
            }
        }
        return keskList;
    }

    public List<Viesti> haeViestitKeskustelulla(int keskusteluId) throws SQLException {
        return this.viestiDao.findKeskusteluId(keskusteluId);
    }

    public void lisaaViesti(int alue, int keskustelu, String nimimerkki, String sisalto) throws SQLException {
        if (nimimerkki.isEmpty() || sisalto.isEmpty()) {
            return;
        }
        Viesti viesti = new Viesti(alue, keskustelu, null, nimimerkki, sisalto, null);
        this.viestiDao.create(viesti);
    }

    public void lisaaAlue(String nimi, String kuvaus){
        Alue alue = new Alue(nimi, kuvaus);
        try{
            this.alueDao.create(alue);
        }catch(SQLException e){
        }
    }

    public boolean lisaaKeskustelu(int alueId, String nimi){
        Keskustelu keskustelu = new Keskustelu(alueId, nimi);
        try{
            this.keskusteluDao.create(keskustelu);
        }catch(SQLException e){
            return false;
        }
        return true;
    }

    public Keskustelu haeKeskusteluNimellä(String otsikko) throws SQLException {
        return this.keskusteluDao.findOneWithNimi(otsikko);
    }

    public Alue haeAlue(int alueId) throws SQLException {
        return this.alueDao.findOne(alueId);
    }

    public Keskustelu haeKeskustelu(int keskusteluId) throws SQLException {
        return this.keskusteluDao.findOne(keskusteluId);
    }

}

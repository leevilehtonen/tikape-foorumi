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
    
    
    public Domain(Database database){
        this.alueDao = new AlueDao(database);
        this.keskusteluDao = new KeskusteluDao(database);
        this.viestiDao = new ViestiDao(database);
    }
    
    public List<Alue> haeAlueet() throws SQLException{
        List<Alue> alueList = alueDao.findAll();
        for(Alue alue : alueList){
            alue.setKeskusteluLkm(this.keskusteluDao.countAlueella(alue.getId()));
            alue.setViestiLkm(this.viestiDao.countAlueella(alue.getId()));
        }
        return alueList;
    }
    
    public List<Keskustelu> haeKeskustelutAlueella(int alueId) throws SQLException{
        List<Keskustelu> keskList = this.keskusteluDao.getAlueella(alueId);
        for(Keskustelu keskustelu : keskList){
            keskustelu.setViestiLkm(this.viestiDao.countKeskustelulla(keskustelu.getId()));
        }
        return keskList;
    }
    
    public List<Viesti> haeViestitKeskustelulla(int keskusteluId) throws SQLException{
        return this.viestiDao.findKeskusteluId(keskusteluId);
    }
}

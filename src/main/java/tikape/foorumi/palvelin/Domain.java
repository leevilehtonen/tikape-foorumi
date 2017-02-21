/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.foorumi.palvelin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        return alueDao.findAll();
    }
    
    public List<Keskustelu> haeKeskustelutAlueella(int alueId) throws SQLException{
        return this.keskusteluDao.getAlueella(alueId);
    }
    
    public List<Viesti> haeViestitKeskustelulla(int keskusteluId) throws SQLException{
        return this.viestiDao.findKeskusteluId(keskusteluId);
    }
}

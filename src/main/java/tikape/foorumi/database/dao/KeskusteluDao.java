package tikape.foorumi.database.dao;

import java.sql.SQLException;
import java.util.List;
import tikape.foorumi.database.Dao;
import tikape.foorumi.database.Database;
import tikape.foorumi.database.collector.KeskusteluCollector;
import tikape.foorumi.domain.Keskustelu;

public class KeskusteluDao implements Dao<Keskustelu, Integer>{
    
    private final Database db;
    
    public KeskusteluDao(Database database){
        this.db = database;
    }
    
    public List<Keskustelu> getAlueella(int alueId) throws SQLException{
        return this.db.queryAndCollect("SELECT * FROM Keskustelu WHERE Keskustelu.alue=?;",new KeskusteluCollector(),alueId);
    }

    @Override
    public Keskustelu findOne(Integer key) throws SQLException {
        return null;
    }

    @Override
    public List<Keskustelu> findAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
    }    
}

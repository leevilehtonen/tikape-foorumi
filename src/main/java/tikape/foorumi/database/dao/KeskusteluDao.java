package tikape.foorumi.database.dao;

import java.sql.SQLException;
import java.util.List;
import tikape.foorumi.database.Dao;
import tikape.foorumi.domain.Keskustelu;

public class KeskusteluDao implements Dao<Keskustelu, Integer>{

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

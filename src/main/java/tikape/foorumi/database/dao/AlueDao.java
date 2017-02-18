package tikape.foorumi.database.dao;

import java.sql.SQLException;
import java.util.List;
import tikape.foorumi.database.Dao;
import tikape.foorumi.domain.Alue;

public class AlueDao implements Dao<Alue, Integer>{

    @Override
    public Alue findOne(Integer key) throws SQLException {
        return null;
    }

    @Override
    public List<Alue> findAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
    }    
}

package tikape.foorumi.database.dao;

import java.sql.SQLException;
import java.util.List;
import tikape.foorumi.database.Dao;
import tikape.foorumi.domain.Viesti;

public class ViestiDao implements Dao<Viesti, Integer>{

    @Override
    public Viesti findOne(Integer key) throws SQLException {
        return null;
    }

    @Override
    public List<Viesti> findAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
    }
}

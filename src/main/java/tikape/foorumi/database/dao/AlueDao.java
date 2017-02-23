package tikape.foorumi.database.dao;

import java.sql.SQLException;
import java.util.List;
import tikape.foorumi.database.Dao;
import tikape.foorumi.database.Database;
import tikape.foorumi.database.collector.AlueCollector;
import tikape.foorumi.domain.Alue;

public class AlueDao implements Dao<Alue, Integer>{
    
    private final Database db;
    
    public AlueDao(Database database){
        this.db = database;
    }

    @Override
    public Alue findOne(Integer key) throws SQLException {
        return null;
    }

    @Override
    public List<Alue> findAll() throws SQLException {
        return this.db.queryAndCollect("SELECT * FROM Alue;", new AlueCollector());
        //return this.db.queryAndCollect("SELECT * FROM Alue;", rs -> new Alue(rs.getInt("id"),rs.getString("otsikko"),rs.getString("kuvaus")));
    }

    @Override
    public void delete(Integer key) throws SQLException {
    }    
}

package tikape.foorumi.database.dao;

import java.sql.SQLException;
import java.util.List;
import tikape.foorumi.database.Collector;
import tikape.foorumi.database.Dao;
import tikape.foorumi.database.Database;
import tikape.foorumi.database.collector.AlueCollector;
import tikape.foorumi.domain.Alue;

public class AlueDao implements Dao<Alue, Integer> {

    private final Database db;
    private final Collector collector;

    public AlueDao(Database database) {
        this.db = database;
        this.collector = new AlueCollector();
    }

    //Testaamaton
    @Override
    public Alue findOne(Integer key) throws SQLException {
        List<Alue> alueList = this.db.queryAndCollect("SELECT * FROM Alue WHERE id = ?;", this.collector, key);
        if(alueList.isEmpty()){
            return null;
        }
        return alueList.get(0);
    }
    


    @Override
    public List<Alue> findAll() throws SQLException {
        return this.db.queryAndCollect("SELECT * FROM Alue ORDER BY otsikko;", this.collector);
    }

    //Testaamaton
    @Override
    public void delete(Integer key) throws SQLException {
        this.db.update("DELETE FROM Alue WHERE id = ?;", key);
    }
    
    public void create(Alue alue) throws SQLException {
        this.db.update("INSERT INTO Alue(otsikko, kuvaus) VALUES(?,?)", alue.getOtsikko(), alue.getKuvaus());
    }
}

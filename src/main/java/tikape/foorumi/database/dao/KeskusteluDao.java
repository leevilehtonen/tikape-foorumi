package tikape.foorumi.database.dao;

import java.sql.SQLException;
import java.util.List;
import tikape.foorumi.database.Collector;
import tikape.foorumi.database.Dao;
import tikape.foorumi.database.Database;
import tikape.foorumi.database.collector.KeskusteluCollector;
import tikape.foorumi.domain.Keskustelu;

public class KeskusteluDao implements Dao<Keskustelu, Integer> {

    private final Database db;
    private final Collector collector;

    public KeskusteluDao(Database database) {
        this.db = database;
        this.collector = new KeskusteluCollector();
    }

    public List<Keskustelu> getAlueella(int alueId) throws SQLException {
        return this.db.queryAndCollect("SELECT * FROM Keskustelu WHERE Keskustelu.alue=?;", this.collector, alueId);
    }

    public int countAlueella(int alueId) throws SQLException {
        return this.db.queryAndCollect("SELECT COUNT(*) as count FROM Keskustelu WHERE alue = ?;", rs -> rs.getInt("count"), alueId).get(0);
    }

    //Testaamaton
    @Override
    public Keskustelu findOne(Integer key) throws SQLException {
        List<Keskustelu> keskList = this.db.queryAndCollect("SELECT * FROM Keskustelu WHERE id = ?;", this.collector, key);
        if (keskList.isEmpty()) {
            return null;
        }
        return keskList.get(0);
    }
    
    public Keskustelu findOneWithNimi(String key) throws SQLException {
        List<Keskustelu> keskList = this.db.queryAndCollect("SELECT * FROM Keskustelu WHERE otsikko = ?;", this.collector, key);
        if (keskList.isEmpty()) {
            return null;
        }
        return keskList.get(0);
    }

    //Testaamaton
    @Override
    public List<Keskustelu> findAll() throws SQLException {
        return this.db.queryAndCollect("SELECT * FROM Keskustelu;", this.collector);
    }

    //Testaamaton
    @Override
    public void delete(Integer key) throws SQLException {
        this.db.update("DELETE FROM Keskustelu WHERE id = ?;", key);
    }

    public void create(Keskustelu keskustelu) throws SQLException {
        this.db.update("INSERT INTO Keskustelu(alue, otsikko) VALUES(?,?)", keskustelu.getAlue(), keskustelu.getOtsikko());
    }
}

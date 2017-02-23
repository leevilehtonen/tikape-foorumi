package tikape.foorumi.database.dao;

import java.sql.SQLException;
import java.util.List;
import tikape.foorumi.database.Collector;
import tikape.foorumi.database.Dao;
import tikape.foorumi.database.Database;
import tikape.foorumi.database.collector.ViestiCollector;
import tikape.foorumi.domain.Viesti;

public class ViestiDao implements Dao<Viesti, Integer> {

    private final Database db;
    private final Collector collector;

    public ViestiDao(Database database) {
        this.db = database;
        this.collector = new ViestiCollector();
    }

    //Testaamaton
    @Override
    public Viesti findOne(Integer key) throws SQLException {
        List<Viesti> viestiList = this.db.queryAndCollect("SELECT * FROM Viesti WHERE id = ?;", this.collector, key);
        if(viestiList.isEmpty()){
            return null;
        }
        return viestiList.get(0);
    }

    public List<Viesti> findKeskusteluId(int keskusteluId) throws SQLException {
        return this.db.queryAndCollect("SELECT * FROM Viesti WHERE keskustelu=?;", this.collector, keskusteluId);
    }
    
    public int countAlueella(int alueId) throws SQLException{
        return this.db.queryAndCollect("SELECT COUNT(*) as count FROM Viesti WHERE alue = ?;", rs -> rs.getInt("count"), alueId).get(0);
    }
    
    public int countKeskustelulla(int keskusteluId) throws SQLException{
        return this.db.queryAndCollect("SELECT COUNT(*) as count FROM Viesti WHERE keskustelu = ?;", rs -> rs.getInt("count"), keskusteluId).get(0);
    }

    //Testaamaton
    @Override
    public List<Viesti> findAll() throws SQLException {
        return this.db.queryAndCollect("SELECT * FROM Viesti;", this.collector);
    }

    //Testaamaton
    @Override
    public void delete(Integer key) throws SQLException {
        this.db.update("DELETE FROM Viesti WHERE id = ?;", key);
    }
}

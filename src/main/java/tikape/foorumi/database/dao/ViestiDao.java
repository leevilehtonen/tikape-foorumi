package tikape.foorumi.database.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import tikape.foorumi.database.Dao;
import tikape.foorumi.database.Database;
import tikape.foorumi.domain.Viesti;

public class ViestiDao implements Dao<Viesti, Integer>{
    
    private final Database db;
    
    public ViestiDao(Database database){
        this.db = database;
    }

    @Override
    public Viesti findOne(Integer key) throws SQLException {
        return null;
    }
    
    public List<Viesti> findKeskusteluId(int keskusteluId) throws SQLException {
        return this.db.queryAndCollect("SELECT * FROM Viesti WHERE keskustelu=?;", rs -> new Viesti(
                rs.getInt("id"),
                rs.getInt("alue"),
                rs.getInt("keskustelu"),
                rs.getInt("replyTo"),
                rs.getString("nimimerkki"),
                rs.getString("viesti"),
                Timestamp.valueOf(rs.getString("lahetysAika"))
        ), keskusteluId);
    }

    @Override
    public List<Viesti> findAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
    }
}

package tikape.foorumi.database.collector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import tikape.foorumi.database.Collector;
import tikape.foorumi.domain.Viesti;

public class ViestiCollector implements Collector<Viesti>{

    @Override
    public Viesti collect(ResultSet set) throws SQLException {
        return new Viesti(
                set.getInt("id"),
                set.getInt("alue"),
                set.getInt("keskustelu"),
                set.getInt("replyTo"),
                set.getString("nimimerkki"),
                set.getString("viesti"),
                Timestamp.valueOf(set.getString("lahetysAika"))
        );
    }
    
}

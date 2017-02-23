package tikape.foorumi.database.collector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import tikape.foorumi.database.Collector;
import tikape.foorumi.domain.Keskustelu;

public class KeskusteluCollector implements Collector<Keskustelu>{

    @Override
    public Keskustelu collect(ResultSet set) throws SQLException {
        return new Keskustelu(
                set.getInt("id"),
                set.getInt("alue"),
                set.getString("otsikko"),
                Timestamp.valueOf(set.getString("luontiAika"))
        );
    }   
}

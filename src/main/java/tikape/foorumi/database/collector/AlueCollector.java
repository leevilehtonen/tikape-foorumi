package tikape.foorumi.database.collector;

import java.sql.ResultSet;
import java.sql.SQLException;
import tikape.foorumi.database.Collector;
import tikape.foorumi.domain.Alue;

public class AlueCollector implements Collector<Alue>{

    @Override
    public Alue collect(ResultSet set) throws SQLException {
        return new Alue(set.getInt("id"),set.getString("otsikko"),set.getString("kuvaus"));
    }
}

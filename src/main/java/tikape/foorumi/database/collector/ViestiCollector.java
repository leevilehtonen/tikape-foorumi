package tikape.foorumi.database.collector;

import java.sql.ResultSet;
import java.sql.SQLException;
import tikape.foorumi.database.Collector;
import tikape.foorumi.domain.Viesti;

public class ViestiCollector implements Collector<Viesti>{

    @Override
    public Viesti collect(ResultSet set) throws SQLException {
        return null;
    }
    
}

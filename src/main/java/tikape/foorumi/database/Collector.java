package tikape.foorumi.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Collector<T> {
    T collect(ResultSet set) throws SQLException;
}

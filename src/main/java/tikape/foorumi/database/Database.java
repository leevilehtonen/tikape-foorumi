package tikape.foorumi.database;

import java.net.URI;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        if (this.databaseAddress.contains("postgres")) {
            try {
                URI dbUri = new URI(System.getenv("DATABASE_URL"));

                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];
                String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

                return DriverManager.getConnection(dbUrl, username, password);

            } catch (Throwable t) {
                System.out.println("Error: " + t.getMessage());
                t.printStackTrace();
            }
        }

        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = null;
        if (this.databaseAddress.contains("postgres")) {
            lauseet = postgreLauseet();
        } else {
            lauseet = sqliteLauseet();
        }

        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    public <T> List<T> queryAndCollect(String query, Collector<T> col, Object... params) throws SQLException {

        List<T> rows = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            rows.add(col.collect(rs));
        }

        rs.close();
        stmt.close();
        return rows;
    }

    public int update(String updateQuery, Object... params) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(updateQuery);

        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }

        int changes = stmt.executeUpdate();
        stmt.close();
        return changes;
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        lista.add("CREATE TABLE Alue (id INTEGER PRIMARY KEY, otsikko VARCHAR(300) NOT NULL UNIQUE, kuvaus VARCHAR(600) NOT NULL UNIQUE);");
        lista.add("CREATE TABLE Keskustelu (id INTEGER PRIMARY KEY, alue INTEGER NOT NULL, otsikko VARCHAR(300) NOT NULL, luontiAika TIMESTAMP DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY(alue) REFERENCES Alue(id));");
        lista.add("CREATE TABLE Viesti (id INTEGER PRIMARY KEY, alue INTEGER NOT NULL, keskustelu INTEGER NOT NULL, nimimerkki VARCHAR(100) NOT NULL, viesti VARCHAR(4000) NOT NULL, lahetysAika TIMESTAMP DEFAULT CURRENT_TIMESTAMP, replyTo INTEGER, FOREIGN KEY(alue) REFERENCES Alue(id), FOREIGN KEY(keskustelu) REFERENCES Keskutelu(id), FOREIGN KEY(replyTo) REFERENCES Viesti(id));");
//        lista.add("INSERT INTO Alue (otsikko, kuvaus) VALUES('Koulu', 'Testialue koulukeskusteluille');");
//        lista.add("INSERT INTO Alue (otsikko, kuvaus) VALUES('Työ', 'Kaikki töihin liittyvä');");
//        lista.add("INSERT INTO Alue (otsikko, kuvaus) VALUES('Vapaa-aika', 'Testialue vapaa-aikaan liittyen');");
//        lista.add("INSERT INTO Alue (otsikko, kuvaus) VALUES('Osta/Myy', 'Testialue Osta/myykeskusteluille');");
//        lista.add("INSERT INTO Alue (otsikko, kuvaus) VALUES('Viihde', 'Testialue viihdekeskusteluille');");
//        lista.add("INSERT INTO Keskustelu (alue, otsikko) VALUES(1, 'Tikape');");
//        lista.add("INSERT INTO Keskustelu (alue, otsikko) VALUES(1, 'OhPe');");
//        lista.add("INSERT INTO Keskustelu (alue, otsikko) VALUES(1, 'OhJa');");
//        lista.add("INSERT INTO Keskustelu (alue, otsikko) VALUES(1, 'JYM');");

        return lista;
    }

    private List<String> postgreLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        lista.add("CREATE TABLE Alue (id SERIAL PRIMARY KEY, otsikko VARCHAR(300) UNIQUE, kuvaus VARCHAR(600) UNIQUE);");
        lista.add("CREATE TABLE Keskustelu (id SERIAL PRIMARY KEY, alue INTEGER REFERENCES Alue, otsikko VARCHAR(300), luontiAika TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");
        lista.add("CREATE TABLE Viesti (id SERIAL PRIMARY KEY, alue INTEGER REFERENCES Alue, keskustelu INTEGER REFERENCES Keskustelu, nimimerkki VARCHAR(100), viesti VARCHAR(4000), lahetysAika TIMESTAMP DEFAULT CURRENT_TIMESTAMP, replyTo INTEGER);");

        return lista;
    }
}

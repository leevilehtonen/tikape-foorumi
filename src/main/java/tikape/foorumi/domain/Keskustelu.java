package tikape.foorumi.domain;

import java.sql.Timestamp;

public class Keskustelu {

    private Integer id;
    private Integer alue;
    private String otsikko;
    private Timestamp luontiAika;
    private int viestiLkm;
    private String viimeisinNimimerkki;
    private Timestamp viimeisinAika;

    public Keskustelu(Integer id, Integer alue, String otsikko, Timestamp luontiAika) {
        this.id = id;
        this.alue = alue;
        this.otsikko = otsikko;
        this.luontiAika = luontiAika;
    }

    public Keskustelu(Integer alue, String otsikko, Timestamp luontiAika) {
        this(null, alue, otsikko, luontiAika);
    }

    public Keskustelu(Integer alue, String otsikko) {
        this(null, alue, otsikko, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlue() {
        return alue;
    }

    public void setAlue(Integer alue) {
        this.alue = alue;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public Timestamp getLuontiAika() {
        return luontiAika;
    }

    public void setLuontiAika(Timestamp luontiAika) {
        this.luontiAika = luontiAika;
    }

    public int getViestiLkm() {
        return this.viestiLkm;
    }

    public void setViestiLkm(int viestiLkm) {
        this.viestiLkm = viestiLkm;
    }
    
    public Timestamp getViimeisinAika() {
        return viimeisinAika;
    }

    public void setViimeisinAika(Timestamp viimeisinAika) {
        this.viimeisinAika = viimeisinAika;
    }

    public String getViimeisinNimimerkki() {
        return viimeisinNimimerkki;
    }

    public void setViimeisinNimimerkki(String viimeisinNimimerkki) {
        this.viimeisinNimimerkki = viimeisinNimimerkki;
    }
}

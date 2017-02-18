package tikape.foorumi.domain;

import java.sql.Timestamp;

public class Viesti {

    private Integer id;
    private Integer alue;
    private Integer keskustelu;
    private Integer replyTo;
    private String nimimerkki;
    private String viesti;
    private Timestamp lahetysAika;

    public Viesti(Integer id, Integer alue, Integer keskustelu, Integer replyTo, String nimimerkki, String viesti, Timestamp lahetysAika) {
        this.id = id;
        this.alue = alue;
        this.keskustelu = keskustelu;
        this.replyTo = replyTo;
        this.nimimerkki = nimimerkki;
        this.viesti = viesti;
        this.lahetysAika = lahetysAika;
    }

    public Viesti(Integer alue, Integer keskustelu, Integer replyTo, String nimimerkki, String viesti, Timestamp lahetysAika) {
        this(null, alue, keskustelu, replyTo, nimimerkki, viesti, lahetysAika);
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

    public Integer getKeskustelu() {
        return keskustelu;
    }

    public void setKeskustelu(Integer keskustelu) {
        this.keskustelu = keskustelu;
    }

    public Integer getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Integer replyTo) {
        this.replyTo = replyTo;
    }

    public String getNimimerkki() {
        return nimimerkki;
    }

    public void setNimimerkki(String nimimerkki) {
        this.nimimerkki = nimimerkki;
    }

    public String getViesti() {
        return viesti;
    }

    public void setViesti(String viesti) {
        this.viesti = viesti;
    }

    public Timestamp getLahetysAika() {
        return lahetysAika;
    }

    public void setLahetysAika(Timestamp lahetysAika) {
        this.lahetysAika = lahetysAika;
    }
    
    
}

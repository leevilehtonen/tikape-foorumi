package tikape.foorumi.domain;

public class Alue {

    private Integer id;
    private String otsikko;
    private String kuvaus;
    private int keskusteluLkm;
    private int viestiLkm;

    public Alue(Integer id, String otsikko, String kuvaus) {
        this.id = id;
        this.otsikko = otsikko;
        this.kuvaus = kuvaus;
    }

    public Alue(String otsikko, String kuvaus) {
        this(null, otsikko, kuvaus);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public int getKeskusteluLkm() {
        return this.keskusteluLkm;
    }

    public void setKeskusteluLkm(int keskusteluLkm) {
        this.keskusteluLkm = keskusteluLkm;
    }

    public int getViestiLkm() {
        return this.viestiLkm;
    }

    public void setViestiLkm(int viestiLkm) {
        this.viestiLkm = viestiLkm;
    }
}

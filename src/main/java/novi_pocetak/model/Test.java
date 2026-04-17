package novi_pocetak.model;

public class Test {
    private final int testId;
    private String oblast;
    private String naziv;
    private int cena;
    private int rezultat;
    private int seansaId;

    public Test(int testId, String oblast, String naziv, int cena, int rezultat, int seansaId) {
        this.testId = testId;
        this.oblast = oblast;
        this.naziv = naziv;
        this.cena = cena;
        this.rezultat = rezultat;
        this.seansaId = seansaId;
    }

    public int getTestId() {
        return testId;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getRezultat() {
        return rezultat;
    }

    public void setRezultat(int rezultat) {
        this.rezultat = rezultat;
    }

    public int getSeansaId() {
        return seansaId;
    }

    public void setSeansaId(int seansaId) {
        this.seansaId = seansaId;
    }
}

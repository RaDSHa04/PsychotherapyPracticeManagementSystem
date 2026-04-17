package novi_pocetak.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Seansa {
    private final int seansaId;
    private LocalDate dan;
    private Time vreme;
    private Time trajanje;
    private String imeKlijenta;
    private String prezimeKlijenta;
    private String opisSeanse;
    private boolean objavljivanjePodataka;
    private String razlogObjavljivanja;
    private String komeSuObjavljeni;
    private LocalDate datumObjavljivanja;

    public Seansa(int seansaId, LocalDate dan,Time vreme, Time trajanje, String imeKlijenta, String prezimeKlijenta, String opisSeanse, boolean objavljivanjePodataka, String razlogObjavljivanja, String komeSuObjavljeni, LocalDate datumObjavljivanja) {
        this.seansaId = seansaId;
        this.dan = dan;
        this.vreme = vreme;
        this.trajanje = trajanje;
        this.imeKlijenta = imeKlijenta;
        this.prezimeKlijenta = prezimeKlijenta;
        this.opisSeanse = opisSeanse;
        this.objavljivanjePodataka = objavljivanjePodataka;
        this.razlogObjavljivanja = razlogObjavljivanja;
        this.komeSuObjavljeni = komeSuObjavljeni;
        this.datumObjavljivanja = datumObjavljivanja;
    }

    public int getSeansaId() {
        return seansaId;
    }

    public LocalDate getDan() {
        return dan;
    }

    public void setDan(LocalDate dan) {
        this.dan = dan;
    }

    public Time getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Time trajanje) {
        this.trajanje = trajanje;
    }

    public String getImeKlijenta() {
        return imeKlijenta;
    }

    public void setImeKlijenta(String imeKlijenta) {
        this.imeKlijenta = imeKlijenta;
    }

    public String getPrezimeKlijenta() {
        return prezimeKlijenta;
    }

    public void setPrezimeKlijenta(String prezimeKlijenta) {
        this.prezimeKlijenta = prezimeKlijenta;
    }

    public String getOpisSeanse() {
        return opisSeanse;
    }

    public void setOpisSeanse(String opisSeanse) {
        this.opisSeanse = opisSeanse;
    }

    public boolean isObjavljivanjePodataka() {
        return objavljivanjePodataka;
    }

    public void setObjavljivanjePodataka(boolean objavljivanjePodataka) {
        this.objavljivanjePodataka = objavljivanjePodataka;
    }

    public String getRazlogObjavljivanja() {
        return razlogObjavljivanja;
    }

    public void setRazlogObjavljivanja(String razlogObjavljivanja) {
        this.razlogObjavljivanja = razlogObjavljivanja;
    }

    public String getKomeSuObjavljeni() {
        return komeSuObjavljeni;
    }

    public void setKomeSuObjavljeni(String komeSuObjavljeni) {
        this.komeSuObjavljeni = komeSuObjavljeni;
    }

    public Time getVreme() {
        return vreme;
    }

    public void setVreme(Time vreme) {
        this.vreme = vreme;
    }

    public LocalDate getDatumObjavljivanja() {
        return datumObjavljivanja;
    }

    public void setDatumObjavljivanja(LocalDate datumObjavljivanja) {
        this.datumObjavljivanja = datumObjavljivanja;
    }
}

package novi_pocetak.model;

import java.time.LocalDate;

public class Psychotherapist {
    private final int psihijatarId;
    private String ime;
    private String prezime;
    private String jmbg;
    private LocalDate datumRodjenja;
    private String prebivaliste;
    private String brojTelefona;
    private String email;
    private LocalDate datumSertifikacije;
    private String lozinka;
    private int oblast_psihoterapije_oblast_id;
    private int centar_za_obuku_centar_id;
    private String nazivOblasti;

    public Psychotherapist(int psihijatarId, String ime, String prezime, String jmbg, LocalDate datumRodjenja, String prebivaliste, String brojTelefona, String email, LocalDate datumSertifikacije, String lozinka, int oblast_psihoterapije_oblast_id, int centar_za_obuku_centar_id) {
        this.psihijatarId = psihijatarId;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.datumRodjenja = datumRodjenja;
        this.prebivaliste = prebivaliste;
        this.brojTelefona = brojTelefona;
        this.email = email;
        this.datumSertifikacije = datumSertifikacije;
        this.lozinka = lozinka;
        this.oblast_psihoterapije_oblast_id = oblast_psihoterapije_oblast_id;
        this.centar_za_obuku_centar_id = centar_za_obuku_centar_id;
    }

    public Psychotherapist(int psihijatarId, String ime, String prezime, String email, String brTelefona, LocalDate datumSertifikacije, String nazivOblasti) {
        this.psihijatarId = psihijatarId;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.brojTelefona = brTelefona;
        this.datumSertifikacije = datumSertifikacije;
        this.nazivOblasti = nazivOblasti;
    }

    public int getPsihijatarId() {
        return psihijatarId;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getPrebivaliste() {
        return prebivaliste;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public String getLozinka(){
        return lozinka;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDatumSertifikacije() {
        return datumSertifikacije;
    }

    public int getOblast_psihoterapije_oblast_id() {
        return oblast_psihoterapije_oblast_id;
    }

    public int getCentar_za_obuku_centar_id() {
        return centar_za_obuku_centar_id;
    }

    public String getNazivOblasti() {
        return nazivOblasti;
    }

    public void setNazivOblasti(String nazivOblasti) {
        this.nazivOblasti = nazivOblasti;
    }
}

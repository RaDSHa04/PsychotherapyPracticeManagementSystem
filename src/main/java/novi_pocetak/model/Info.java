package novi_pocetak.model;

import java.sql.Date;

public class Info {
    //p.psihoterapeut_id, p.ime, p.prezime, p.mejl, p.brojtelefona, p.datumSertifikacije, o.naziv
    private final int psihoterapeutId;
    private String ime;
    private String prezime;
    private String email;
    private String brojTelefona;
    private Date datumSertifikacije;
    private String nazivOblasti;

    public Info(int psihoterapeutId, String ime, String prezime, String email, String brojTelefona, Date datumSertifikacije, String nazivOblasti) {
        this.psihoterapeutId = psihoterapeutId;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.datumSertifikacije = datumSertifikacije;
        this.nazivOblasti = nazivOblasti;
    }

    public int getPsihoterapeutId() {
        return psihoterapeutId;
    }
    public String getIme() {
        return ime;
    }
    public String getPrezime() {
        return prezime;
    }
    public String getEmail() {
        return email;
    }
    public String getBrojTelefona() {
        return brojTelefona;
    }
    public Date getDatumSertifikacije() {
        return datumSertifikacije;
    }
    public String getNazivOblasti() {
        return nazivOblasti;
    }
}

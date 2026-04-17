package novi_pocetak.model;

public class Klijent {
    private final int klijentId;
    private String ime;
    private String prezime;
    private String email;
    private String brojTelefona;

    public Klijent(int klijentId, String ime, String prezime, String email, String brojTelefona) {
        this.klijentId = klijentId;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.brojTelefona = brojTelefona;
    }
    public int getKlijentId() {
        return klijentId;
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
    public String getBrojTelefona(){
        return brojTelefona;
    }
}

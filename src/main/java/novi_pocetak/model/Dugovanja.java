package novi_pocetak.model;

import java.time.LocalDate;
import java.util.Date;

public class Dugovanja {
    private int seansa_id;
    private int klijent_id;
    private LocalDate datumSeanse;
    private int uplaceno;
    private int cena;
    private int dug;

    public Dugovanja(int seansa_id, int klijent_id, LocalDate datumSeanse, int uplaceno, int cena, int dug) {
        this.seansa_id = seansa_id;
        this.klijent_id = klijent_id;
        this.datumSeanse = datumSeanse;
        this.uplaceno = uplaceno;
        this.cena = cena;
        this.dug = dug;
    }

    public int getSeansa_id() {
        return seansa_id;
    }

    public void setSeansa_id(int seansa_id) {
        this.seansa_id = seansa_id;
    }

    public int getKlijent_id() {
        return klijent_id;
    }

    public void setKlijent_id(int klijent_id) {
        this.klijent_id = klijent_id;
    }

    public LocalDate getDatumSeanse() {
        return datumSeanse;
    }

    public void setDatumSeanse(LocalDate datumSeanse) {
        this.datumSeanse = datumSeanse;
    }

    public int getUplaceno() {
        return uplaceno;
    }

    public void setUplaceno(int uplaceno) {
        this.uplaceno = uplaceno;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getDug() {
        return dug;
    }

    public void setDug(int dug) {
        this.dug = dug;
    }
}

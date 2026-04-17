package novi_pocetak.controller;

import novi_pocetak.model.Psychotherapist;
import novi_pocetak.model.base.Server;

public class ImePrezimePs {
    private String ime;
    private String prezime;
    private Psychotherapist psychotherapist;

    public ImePrezimePs() {
        psychotherapist= Server.getInstance().getSelected();
        ime=psychotherapist.getIme();
        prezime=psychotherapist.getPrezime();
    }
    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }
}

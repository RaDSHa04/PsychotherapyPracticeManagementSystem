package novi_pocetak.controller;

import novi_pocetak.model.Klijent;
import novi_pocetak.model.Psychotherapist;
import novi_pocetak.model.base.Server;
import novi_pocetak.model.utility.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class SviKlijenti {
    private Psychotherapist psychotherapist;
    private List<Klijent> klijentLista=new ArrayList<>();

    public SviKlijenti() {
        this.psychotherapist= Server.getInstance().getSelected();
        this.klijentLista= JDBCUtils.dobijKlijente(psychotherapist);
    }

    public List<Klijent> getKlijentLista() {
        return klijentLista;
    }
}

package novi_pocetak.model.base;

import novi_pocetak.model.Dugovanja;
import novi_pocetak.model.Psychotherapist;
import novi_pocetak.model.Seansa;
import novi_pocetak.model.utility.JDBCUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Server {
    public static Server instance;
    private final List<Psychotherapist> psychotherapists= new ArrayList<>();
    private final List<Dugovanja> dugovanja= new ArrayList<>();
    private final List<Seansa> seanse = new ArrayList<>();
    private Psychotherapist selected; //ovde ce se nalaziti prijavljeni
    private int brPsihoterapeuta;

    private Server() {
        this.setPsychotherapists(JDBCUtils.selectAllFromPsihoterapeut());
        this.setDugovanje(JDBCUtils.izvuciDugovanja());
        brPsihoterapeuta=psychotherapists.size();
    }

    public static Server getInstance() {
        if(instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public List<Psychotherapist> getPsychotherapists() {
        return psychotherapists;
    }

    public void setPsychotherapists(Collection<Psychotherapist> psychotherapists) {
        this.psychotherapists.clear();
        this.psychotherapists.addAll(psychotherapists);
    }

    public Psychotherapist getSelected() {
        return selected;
    }

    public void setSelected(Psychotherapist selected) {
        this.selected = selected;
    }

    public int getBrPsihoterapeuta() {
        return brPsihoterapeuta;
    }

    public void setBrPsihoterapeuta(int brPsihoterapeuta) {
        this.brPsihoterapeuta = brPsihoterapeuta;
    }

    public List<Dugovanja> getDugovanja() {
        return dugovanja;
    }

    public void setDugovanje(Collection<Dugovanja> dugovanje){
        this.dugovanja.clear();
        this.dugovanja.addAll(dugovanje);
    }

    public void setSeanse(Collection<Seansa> seanse){
        this.seanse.clear();
        this.seanse.addAll(seanse);
    }
}

package novi_pocetak.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import novi_pocetak.model.Psychotherapist;
import novi_pocetak.model.base.Server;
import novi_pocetak.model.utility.JDBCUtils;

import java.sql.Date;
import java.util.List;

public class InsertPs implements EventHandler<ActionEvent> {
    private TextField tfIme;
    private TextField tfPrezime;
    private TextField tfJmbg;
    private TextField tfDatumRodjenja;
    private TextField tfPrebivaliste;
    private TextField tfBrojTelefona;
    private TextField tfEmail;
    private TextField tfDatumSertifikacije;
    private TextField tfLozinka;
    private TextField tfOblastId;
    private TextField tfCentarId;

    public InsertPs(TextField tfIme, TextField tfPrezime,TextField tfJmbg, TextField tfDatumRodjenja,
                    TextField tfPrebivaliste, TextField tfBrojTelefona, TextField tfEmail,
                    TextField tfDatumSertifikacije, TextField tfLozinka, TextField tfOblastId,
                    TextField tfCentarId){
        this.tfIme = tfIme;
        this.tfPrezime = tfPrezime;
        this.tfJmbg = tfJmbg;
        this.tfDatumRodjenja = tfDatumRodjenja;
        this.tfPrebivaliste = tfPrebivaliste;
        this.tfBrojTelefona = tfBrojTelefona;
        this.tfEmail = tfEmail;
        this.tfDatumSertifikacije = tfDatumSertifikacije;
        this.tfLozinka = tfLozinka;
        this.tfOblastId = tfOblastId;
        this.tfCentarId = tfCentarId;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(tfIme.getText().isEmpty() || tfPrezime.getText().isEmpty() || tfJmbg.getText().isEmpty()
                || tfDatumRodjenja.getText().isEmpty() || tfPrebivaliste.getText().isEmpty() ||
                tfBrojTelefona.getText().isEmpty() || tfEmail.getText().isEmpty() ||
                tfDatumSertifikacije.getText().isEmpty() || tfLozinka.getText().isEmpty() ||
                tfOblastId.getText().isEmpty() || tfCentarId.getText().isEmpty()){
            System.out.println("PRAZNO!");
            return;
        }
        String ime=tfIme.getText().trim();
        String prezime=tfPrezime.getText().trim();
        String jmbg=tfJmbg.getText().trim();
        Date datumRodjenja=Date.valueOf(tfDatumRodjenja.getText().trim());
        String prebivaliste=tfPrebivaliste.getText().trim();
        String brojTelefona=tfBrojTelefona.getText().trim();
        String email=tfEmail.getText().trim();
        Date datumSertifikacije=Date.valueOf(tfDatumSertifikacije.getText().trim());
        String lozinka=tfLozinka.getText().trim();
        int oblastId=Integer.parseInt(tfOblastId.getText().trim());
        int centarId=Integer.parseInt(tfCentarId.getText().trim());
        JDBCUtils.insertPsychotherapist(ime, prezime, jmbg, datumRodjenja, prebivaliste, brojTelefona,
                email, datumSertifikacije, lozinka, oblastId, centarId);
        List<Psychotherapist> psList=JDBCUtils.selectAllFromPsihoterapeut();
        Server.getInstance().setPsychotherapists(psList);
        Server.getInstance().setSelected(Server.getInstance().getPsychotherapists().get(Server.getInstance().getPsychotherapists().size()-1));
    }
}

package novi_pocetak.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import novi_pocetak.model.Psychotherapist;
import novi_pocetak.model.base.Server;
import novi_pocetak.model.utility.JDBCUtils;

public class InfoPs implements EventHandler<ActionEvent> {
    private Psychotherapist psychotherapist;

    public InfoPs(Label psihijatar_id, Label ime, Label prezime, Label email, Label brTelefona, Label datumSertifikacije, Label oblast) {
        this.psychotherapist = Server.getInstance().getSelected();
        Psychotherapist ps = JDBCUtils.infoPs(psychotherapist);
        psihijatar_id.setText(String.valueOf(ps.getPsihijatarId()));
        ime.setText(ps.getIme());
        prezime.setText(ps.getPrezime());
        email.setText(ps.getEmail());
        brTelefona.setText(ps.getBrojTelefona());
        datumSertifikacije.setText(ps.getDatumSertifikacije().toString());
        oblast.setText(ps.getNazivOblasti());
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}

package novi_pocetak.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import novi_pocetak.model.Psychotherapist;
import novi_pocetak.model.base.Server;
import novi_pocetak.model.utility.JDBCUtils;

public class LogInPs implements EventHandler<ActionEvent> {
    private TextField tfEmail;
    private TextField tfLozinka;
    private boolean loggedIn;

    public LogInPs(TextField tfEmail, TextField tfLozinka) {
        this.tfEmail = tfEmail;
        this.tfLozinka = tfLozinka;
        this.loggedIn = false;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(tfEmail.getText().isEmpty() || tfLozinka.getText().isEmpty()) {
            return;
        }
        loggedIn=false;
        String email = tfEmail.getText().trim();
        String lozinka = tfLozinka.getText().trim();
        Psychotherapist psychotherapist=JDBCUtils.logPsychotherapist(email, lozinka);
        if(psychotherapist!=null) {
            Server.getInstance().setSelected(psychotherapist);
            loggedIn = true;
        }
    }

    public boolean enableLogin(){
        if(loggedIn)
            return true;
        return false;
    }
}

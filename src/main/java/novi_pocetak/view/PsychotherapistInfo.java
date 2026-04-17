package novi_pocetak.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.*;
import novi_pocetak.controller.InfoPs;

//SELECT
//    p.psihoterapeut_id,
//    p.ime,
//    p.prezime,
//    p.mejl,
//p.brojtelefona,
//    p.datumSertifikacije,
//    o.naziv
//FROM
//    psihoterapeut p
//JOIN
//oblast_psihoterapije o ON p.oblast_psihoterapije_oblast_id = o.oblast_id;
public class PsychotherapistInfo extends VBox {
    private Button btnPovratak=new Button("Povratak");
    private Label lblPsihoterapeutId = new Label("AAA");
    private Label lblPsihoterapeutIme= new Label("AAA");
    private Label lblPsihoterapeutPrezime= new Label("AAA");
    private Label lblPsihoterapeutEmail= new Label("AAA");
    private Label lblPsihoterapeutBrTelefona= new Label("AAA");
    private Label lblPsihoterapeutDatumSertifikacije= new Label("AAA");
    private Label lblOblastNaziv= new Label("AAA");
    private Label lblNaslov=new Label("INFORMACIJE O PSIHOTERAPEUTU");

    public PsychotherapistInfo(Stage glavni, Stage info) {
        setAlignment(Pos.CENTER);
        setSpacing(20);
        info.setTitle("Registracija");
        setPadding(new Insets(20,5,20,5));
        lblNaslov.setFont(new Font("Arial", 20));
        getChildren().add(lblNaslov);
        setMinWidth(550);
        napraviIzgled();
        InfoPs INFO = new InfoPs(lblPsihoterapeutId, lblPsihoterapeutIme, lblPsihoterapeutPrezime, lblPsihoterapeutEmail, lblPsihoterapeutBrTelefona, lblPsihoterapeutDatumSertifikacije, lblOblastNaziv);

        btnPovratak.setOnAction(e -> {
            info.close();
            glavni.show();
        });
    }

    private void napraviIzgled(){
        GridPane gp1=new GridPane();
        gp1.setAlignment(Pos.CENTER);
        gp1.setHgap(10);
        gp1.setVgap(10);
        gp1.add(new Label("Id"), 0, 0);
        gp1.add(lblPsihoterapeutId, 2, 0);
        gp1.add(new Label("Ime"), 0, 1);
        gp1.add(lblPsihoterapeutIme, 2, 1);
        gp1.add(new Label("Prezime"), 0, 2);
        gp1.add(lblPsihoterapeutPrezime, 2, 2);
        gp1.add(new Label("Email"), 0, 3);
        gp1.add(lblPsihoterapeutEmail, 2, 3);
        gp1.add(new Label("Broj telefona"), 0, 4);
        gp1.add(lblPsihoterapeutBrTelefona, 2, 4);
        gp1.add(new Label("Datum Sertifikacije"), 0, 5);
        gp1.add(lblPsihoterapeutDatumSertifikacije, 2, 5);
        gp1.add(new Label("Naziv oblasti"), 0, 6);
        gp1.add(lblOblastNaziv, 2, 6);
        gp1.add(btnPovratak, 1, 8);
        getChildren().add(gp1);

    }
}

package novi_pocetak.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import novi_pocetak.controller.InsertPs;

import java.sql.Date;

public class SingUp extends VBox {
    private Button btnPovratak=new Button("Povratak");
    private Button btnRegistruj=new Button("Registruj");
    private TextField tfIme= new TextField();
    private TextField tfPrezime= new TextField();
    private TextField tfJmbg= new TextField();
    private TextField tfDatumRodjenja= new TextField();
    private TextField tfPrebivaliste= new TextField();
    private TextField tfBrojTelefona= new TextField();
    private TextField tfEmail= new TextField();
    private TextField tfDatumSertifikacije= new TextField();
    private TextField tfLozinka= new TextField();
    private TextField tfOblastId= new TextField();
    private TextField tfCentarId= new TextField();
    private Label lblNaslov=new Label("REGISTRUJ PSIHOTERAPEUTA");

    public SingUp(Stage primaryStage, Stage drugiStage) {
        setAlignment(Pos.CENTER);
        setSpacing(20);
        drugiStage.setTitle("Registracija");
        setPadding(new Insets(10,5,10,5));
        lblNaslov.setFont(new Font("Arial", 20));
        getChildren().add(lblNaslov);
        setMinWidth(550);
        GridPane gp1=new GridPane();
        gp1.setAlignment(Pos.CENTER);
        gp1.setHgap(10);
        gp1.setVgap(10);
        gp1.add(new Label("Ime"), 0, 0);
        gp1.add(tfIme, 1, 0);
        gp1.add(new Label("Prezime"), 0, 1);
        gp1.add(tfPrezime, 1, 1);
        gp1.add(new Label("Jmbg"), 0, 2);
        gp1.add(tfJmbg, 1, 2);
        gp1.add(new Label("Datum Rodjenja"), 0, 3);
        gp1.add(tfDatumRodjenja, 1, 3);
        gp1.add(new Label("Prebivaliste"), 0, 4);
        gp1.add(tfPrebivaliste, 1, 4);
        gp1.add(new Label("Broj Telefona"), 0, 5);
        gp1.add(tfBrojTelefona, 1, 5);
        gp1.add(new Label("Email"), 0, 6);
        gp1.add(tfEmail, 1, 6);
        gp1.add(new Label("Datum Sertifikacije"), 0, 7);
        gp1.add(tfDatumSertifikacije, 1, 7);
        gp1.add(new Label("Lozinka"), 0, 8);
        gp1.add(tfLozinka, 1, 8);
        gp1.add(new Label("Oblast Id"), 0, 9);
        gp1.add(tfOblastId, 1, 9);
        gp1.add(new Label("Centar Id"), 0, 10);
        gp1.add(tfCentarId, 1, 10);
        getChildren().add(gp1);
        HBox hb=new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(10);
        hb.getChildren().add(btnPovratak);
        hb.getChildren().add(btnRegistruj);
        getChildren().add(hb);

        btnPovratak.setOnAction(e  -> {
            drugiStage.close();
            primaryStage.show();
        });

        btnRegistruj.setOnAction(e -> {
            new InsertPs(this.tfIme, this.tfPrezime, this.tfJmbg, this.tfDatumRodjenja,
                    this.tfPrebivaliste, this.tfBrojTelefona, this.tfEmail, this.tfDatumSertifikacije,
                    this.tfLozinka, this.tfOblastId, this.tfCentarId).handle(e);

            Stage s4= new Stage();
            GlavniView root=new GlavniView(s4);
            s4.setScene(new Scene(root));
            s4.show();
            drugiStage.close();
        });
    }
}

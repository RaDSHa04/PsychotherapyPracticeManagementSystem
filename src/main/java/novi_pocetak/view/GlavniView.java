package novi_pocetak.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import novi_pocetak.model.Psychotherapist;
import novi_pocetak.model.base.Server;

public class GlavniView extends VBox {
    private Stage glavniStage;
    private Psychotherapist selected= Server.getInstance().getSelected();
    private Button btnProfil=new Button("Profil psihoterapeuta");
    private Button btnPrijave=new Button("Prijave novih klijenata");
    private Button btnOdrzaneSeanse=new Button("Odrzane Seanse");
    private Button btnBuduciTermini=new Button("Termini buducih seansi");
    private Button btnPlacanje=new Button("Pregledi placanja");
    private Label lblNaslov=new Label("Psihoterapeut: "+selected.getIme()+" "+selected.getPrezime());
    public GlavniView(Stage glavniStage) {
        lblNaslov.setFont(new Font("Arial", 16));
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20,10,20,10));
        setSpacing(20);
        setMinWidth(800);
        glavniStage.setResizable(false);
        glavniStage.setTitle("Glavni prozor");
        this.glavniStage = glavniStage;
        napraviIzgled();

        btnProfil.setOnAction(e -> {
            Stage s2= new Stage();
            PsychotherapistInfo root= new PsychotherapistInfo(glavniStage, s2);
            s2.setScene(new Scene(root));
            s2.show();
            glavniStage.close();
        });

        btnPlacanje.setOnAction(e->{
            Stage s2 = new Stage();
            DugovanjeView root = new DugovanjeView(glavniStage, s2);
            s2.setScene(new Scene(root));
            s2.show();
            glavniStage.close();
        });
        btnOdrzaneSeanse.setOnAction(e->{
            Stage s2 = new Stage();
            SeanseDoDanasView root = new SeanseDoDanasView(glavniStage, s2);
            s2.setScene(new Scene(root));
            s2.show();
            glavniStage.close();
        });

        btnPrijave.setOnAction(e->{
            Stage s2 = new Stage();
            PrijaveKlijenata root=new PrijaveKlijenata(glavniStage, s2);
            s2.setScene(new Scene(root));
            s2.show();
            glavniStage.close();
        });

        btnBuduciTermini.setOnAction(e->{
            Stage s2 = new Stage();
            SeanseOdDanasView root=new SeanseOdDanasView(glavniStage, s2);
            s2.setScene(new Scene(root));
            s2.show();
            glavniStage.close();
        });
    }

    public void napraviIzgled() {
        getChildren().add(lblNaslov);
        GridPane gp=new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(20);
        gp.add(btnProfil, 0, 0);
        gp.add(btnPrijave, 2, 0);
        gp.add(btnOdrzaneSeanse, 0, 1);
        gp.add(btnBuduciTermini, 2, 1);
        gp.add(btnPlacanje, 1, 2);
        getChildren().add(gp);
    }
}

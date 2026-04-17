package novi_pocetak.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import novi_pocetak.model.Seansa;
import novi_pocetak.model.base.Server;
import novi_pocetak.model.utility.JDBCUtils;

public class SeanseDoDanasView extends VBox {
    private Label lblNaslov=new Label("Seanse ulogovanog psihoterapeuta");
    private final TableView tvSeanse = new SeanseTable(JDBCUtils.izvuciSeanseDoDanas(Server.getInstance().getSelected().getPsihijatarId()));
    private Button btnPovratak = new Button("Povratak");
    private Button btnPregled = new Button("Pregled seanse");
    private Button btnObjavlivanje = new Button("Objavljivanje podataka");

    public SeanseDoDanasView(Stage glavni, Stage seanse) {
        lblNaslov.setFont(new Font("Arial", 20));
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setMinWidth(500);
        setPadding(new Insets(20,0,20,0));
        getChildren().add(lblNaslov);
        getChildren().add(tvSeanse);
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(20);
        hb.getChildren().add(btnPovratak);
        hb.getChildren().add(btnPregled);
        hb.getChildren().add(btnObjavlivanje);
        getChildren().add(hb);

        btnPovratak.setOnAction(e -> {
           seanse.close();
           glavni.show();
        });

        btnPregled.setOnAction(e->{
            Seansa selektovanaSeansa = (Seansa) tvSeanse.getSelectionModel().getSelectedItem();
            int seansaId = selektovanaSeansa.getSeansaId();
            Seansa s = JDBCUtils.izvuciPregledSeanse(seansaId);
            PregledView root = new PregledView(s);
            Stage s2= new Stage();
            s2.setScene(new Scene(root));
            s2.show();
        });

        btnObjavlivanje.setOnAction(e->{
            Seansa selektovanaSeansa = (Seansa) tvSeanse.getSelectionModel().getSelectedItem();
            ObjaviView root = new ObjaviView(selektovanaSeansa);
            Stage s2 = new Stage();
            s2.setScene(new Scene(root));
            s2.show();
        });
    }
}

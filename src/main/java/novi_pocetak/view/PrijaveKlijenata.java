package novi_pocetak.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import novi_pocetak.controller.SviKlijenti;
import novi_pocetak.model.base.Server;

public class PrijaveKlijenata extends VBox {
    private Button btnPovratak=new Button("Povratak");
    private final TableView tvKlijenti;

    public PrijaveKlijenata(Stage glavni, Stage prijave) {
        SviKlijenti sviKlijenti = new SviKlijenti();
        tvKlijenti= new KlijentTable(sviKlijenti.getKlijentLista());
        //tvKlijenti=new KlijentTable(Server.getInstance().getKlijenti());
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPadding(new Insets(0,0, 20, 0));
        prijave.setTitle("Prijave novih klijenata");
        setMinWidth(413);
        prijave.setResizable(false);
        napraviIzgled();

        btnPovratak.setOnAction(e -> {
            prijave.close();
            glavni.show();
        });
    }

    public void napraviIzgled() {
        getChildren().add(tvKlijenti);
        getChildren().add(btnPovratak);
    }
}

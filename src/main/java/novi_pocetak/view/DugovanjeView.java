package novi_pocetak.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import novi_pocetak.model.base.Server;

public class DugovanjeView extends VBox {
    private Label lblNaslov=new Label("DUGOVANJA");
    private final TableView tvDugovanja= new DugovanjeTable(Server.getInstance().getDugovanja());
    private Button btnPovratak=new Button("Povratak");

    public DugovanjeView(Stage glavni, Stage dugovanje){
        setPadding(new Insets(20,0,20,0));
        lblNaslov.setFont(new Font("Arial", 20));
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setMinWidth(507);
        getChildren().add(lblNaslov);
        getChildren().add(tvDugovanja);
        getChildren().add(btnPovratak);

        btnPovratak.setOnAction(e -> {
            dugovanje.close();
            glavni.show();
        });
    }
}

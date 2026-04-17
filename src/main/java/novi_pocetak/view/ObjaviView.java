package novi_pocetak.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import novi_pocetak.model.Seansa;
import novi_pocetak.model.utility.JDBCUtils;

public class ObjaviView extends VBox {
    public ObjaviView(Seansa s){
        GridPane gp = new GridPane();
        setSpacing(20);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20,10,20,10));
        Label l1 = new Label("Kome se objavljuje:");
        Label l2 = new Label("Razlog objavljivanja:");
        Button btnObjavi = new Button("Objavi");
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        gp.setVgap(20);
        gp.setHgap(10);
        gp.add(l1, 1, 1);
        gp.add(l2, 1, 2);
        gp.add(tf1, 2, 1);
        gp.add(tf2, 2, 2);
        getChildren().add(gp);
        getChildren().add(btnObjavi);

        btnObjavi.setOnAction(e->{
            if(!s.isObjavljivanjePodataka())
                JDBCUtils.objavaPodataka(s, tf2.getText().trim(), tf1.getText().trim());
        });
    }
}

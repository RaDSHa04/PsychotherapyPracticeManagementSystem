package novi_pocetak.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import novi_pocetak.model.Seansa;
import novi_pocetak.model.base.Server;
import novi_pocetak.model.utility.JDBCUtils;

import javax.swing.text.LabelView;

public class PregledView extends VBox {
    public PregledView(Seansa s){
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setMinWidth(800);
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        Label l1 = new Label("Datum:");
        gp.add(l1, 1 ,1);
        Label l2 = new Label(s.getDan().toString());
        gp.add(l2, 2, 1);
        Label l3 = new Label("Vreme:");
        Label l4 = new Label(s.getVreme().toString());
        Label l5 = new Label("Trajanje:");
        Label l6 = new Label(s.getTrajanje().toString());
        Label l7 = new Label("Klijent:");
        Label l8 = new Label(s.getImeKlijenta() + " " + s.getPrezimeKlijenta());
        gp.add(l3, 1, 2);
        gp.add(l4, 2, 2);
        gp.add(l5, 1, 3);
        gp.add(l6, 2, 3);
        gp.add(l7, 1, 4);
        gp.add(l8, 2, 4);
        getChildren().add(new Label("Pregled selektovane seanse"));
        getChildren().add(gp);
        getChildren().add(new Label("Opis seanse:"));
        TextArea ta = new TextArea(s.getOpisSeanse());
        ta.setEditable(false);
        ta.setFocusTraversable(false);
        ta.setWrapText(true);
        getChildren().add(ta);
        Label l9 = new Label("Objavljeni podaci:");
        getChildren().add(l9);
        if(s.isObjavljivanjePodataka()){
            Label l10 = new Label("Da");
            Label l11 = new Label(s.getKomeSuObjavljeni());
            Label l12 = new Label(s.getRazlogObjavljivanja());
            Label l13 = new Label(s.getDatumObjavljivanja().toString());
            getChildren().add(l10);
            getChildren().add(l11);
            getChildren().add(l12);
            getChildren().add(l13);
        }
        else{
            Label l10 = new Label("Ne");
            Label l11 = new Label("/");
            Label l12 = new Label("/");
            Label l13 = new Label("/");
            getChildren().add(l10);
            getChildren().add(l11);
            getChildren().add(l12);
            getChildren().add(l13);
        }
        TableView tvTestovi = new TestoviTable(JDBCUtils.izvuciTestoveZaSeansu(s.getSeansaId()));
        getChildren().add(tvTestovi);
    }
}

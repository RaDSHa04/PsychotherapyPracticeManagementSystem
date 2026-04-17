package novi_pocetak.view;

import com.mysql.cj.xdevapi.Table;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import novi_pocetak.model.Test;

import java.util.List;

public class TestoviTable extends TableView<Test> {
    public TestoviTable(List<Test> values){
        super(FXCollections.observableArrayList(values));

        TableColumn<Test, Integer> tcIdTesta = new TableColumn<>("Id testa");
        TableColumn<Test, String> tcOblast = new TableColumn<>("Oblast");
        TableColumn<Test, String> tcNaziv = new TableColumn<>("Naziv");
        TableColumn<Test, Integer> tcCena = new TableColumn<>("Cena");
        TableColumn<Test, Integer> tcRezultat = new TableColumn<>("Rezultat");

        tcIdTesta.setCellValueFactory(new PropertyValueFactory<>("testId"));
        tcOblast.setCellValueFactory(new PropertyValueFactory<>("oblast"));
        tcNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        tcCena.setCellValueFactory(new PropertyValueFactory<>("cena"));
        tcRezultat.setCellValueFactory(new PropertyValueFactory<>("rezultat"));

        super.getColumns().add(tcIdTesta);
        super.getColumns().add(tcOblast);
        super.getColumns().add(tcNaziv);
        super.getColumns().add(tcCena);
        super.getColumns().add(tcRezultat);
    }
}

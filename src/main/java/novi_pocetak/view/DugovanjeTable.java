package novi_pocetak.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import novi_pocetak.model.Dugovanja;
import novi_pocetak.model.Psychotherapist;

import java.time.LocalDate;
import java.util.List;

public class DugovanjeTable extends TableView<Dugovanja> {
    public DugovanjeTable(List<Dugovanja> values) {
        super(FXCollections.observableArrayList(values));

        TableColumn<Dugovanja, Integer> tcSeansaId= new TableColumn<>("ID Seanse");
        TableColumn<Dugovanja, Integer> tcKlijentId = new TableColumn<>("ID Klijenta");
        TableColumn<Dugovanja, LocalDate> tcDatum= new TableColumn<>("Datum seanse");
        TableColumn<Dugovanja, Integer> tcCena = new TableColumn<>("Cena seanse");
        TableColumn<Dugovanja, Integer> tcPlaceno = new TableColumn<>("Placeno do sad");
        TableColumn<Dugovanja, Integer> tcDug = new TableColumn<>("Dugovanje");

        tcSeansaId.setCellValueFactory(new PropertyValueFactory<>("seansa_id"));
        tcKlijentId.setCellValueFactory(new PropertyValueFactory<>("klijent_id"));
        tcDatum.setCellValueFactory(new PropertyValueFactory<>("datumSeanse"));
        tcCena.setCellValueFactory(new PropertyValueFactory<>("uplaceno"));
        tcPlaceno.setCellValueFactory(new PropertyValueFactory<>("cena"));
        tcDug.setCellValueFactory(new PropertyValueFactory<>("dug"));

        super.getColumns().add(tcSeansaId);
        super.getColumns().add(tcKlijentId);
        super.getColumns().add(tcDatum);
        super.getColumns().add(tcCena);
        super.getColumns().add(tcPlaceno);
        super.getColumns().add(tcDug);
    }
}

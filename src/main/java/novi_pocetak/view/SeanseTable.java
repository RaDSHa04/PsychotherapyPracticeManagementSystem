package novi_pocetak.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import novi_pocetak.model.Seansa;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class SeanseTable extends TableView<Seansa> {
    public SeanseTable(List<Seansa> values){
        super(FXCollections.observableArrayList(values));

        TableColumn<Seansa, Integer> tcId = new TableColumn<>("Id seanse");
        TableColumn<Seansa, LocalDate> tcDan = new TableColumn<>("Datum");
        TableColumn<Seansa, Time> tcVreme = new TableColumn<>("Vreme");
        TableColumn<Seansa, Time> tcTrajanje = new TableColumn<>("Trajanje");
        TableColumn<Seansa, String> tcImeKlijenta = new TableColumn<>("Ime klijenta");
        TableColumn<Seansa, String> tcPrezimeKlijenta = new TableColumn<>("Prezime klijenta");

        tcId.setCellValueFactory(new PropertyValueFactory<>("seansaId"));
        tcDan.setCellValueFactory(new PropertyValueFactory<>("dan"));
        tcVreme.setCellValueFactory(new PropertyValueFactory<>("vreme"));
        tcTrajanje.setCellValueFactory(new PropertyValueFactory<>("trajanje"));
        tcImeKlijenta.setCellValueFactory(new PropertyValueFactory<>("imeKlijenta"));
        tcPrezimeKlijenta.setCellValueFactory(new PropertyValueFactory<>("prezimeKlijenta"));

        super.getColumns().add(tcId);
        super.getColumns().add(tcDan);
        super.getColumns().add(tcVreme);
        super.getColumns().add(tcTrajanje);
        super.getColumns().add(tcImeKlijenta);
        super.getColumns().add(tcPrezimeKlijenta);
    }
}

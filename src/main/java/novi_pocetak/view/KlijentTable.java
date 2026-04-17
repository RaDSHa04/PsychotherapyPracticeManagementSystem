package novi_pocetak.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import novi_pocetak.model.Klijent;
import java.util.List;

public class KlijentTable extends TableView<Klijent> {
    public KlijentTable(List<Klijent> values) {
        super(FXCollections.observableArrayList(values));

        TableColumn<Klijent, Integer> tcKlijentId= new TableColumn<>("ID");
        TableColumn<Klijent, String> tcKlijentIme= new TableColumn<>("Ime");
        TableColumn<Klijent, String> tcKlijentPrezime= new TableColumn<>("Prezime");
        TableColumn<Klijent, String> tcKlijentEmail= new TableColumn<>("Email");
        TableColumn<Klijent, String> tcKlijentBrojTelefona= new TableColumn<>("Broj telefona");

        tcKlijentId.setCellValueFactory(new PropertyValueFactory<>("klijentId"));
        tcKlijentIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        tcKlijentPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        tcKlijentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcKlijentBrojTelefona.setCellValueFactory(new PropertyValueFactory<>("brojTelefona"));

        super.getColumns().add(tcKlijentId);
        super.getColumns().add(tcKlijentIme);
        super.getColumns().add(tcKlijentPrezime);
        super.getColumns().add(tcKlijentEmail);
        super.getColumns().add(tcKlijentBrojTelefona);
    }
}

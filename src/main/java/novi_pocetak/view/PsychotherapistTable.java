package novi_pocetak.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import novi_pocetak.model.Psychotherapist;

import java.time.LocalDate;
import java.util.List;

public class PsychotherapistTable extends TableView<Psychotherapist> {
    public PsychotherapistTable(List<Psychotherapist> values) {
        super(FXCollections.observableArrayList(values));

        TableColumn<Psychotherapist, Integer> tcPsihijatarId= new TableColumn<>("ID");
        TableColumn<Psychotherapist, String> tcPsihijatarIme = new TableColumn<>("Ime");
        TableColumn<Psychotherapist, String> tcPsihijatarPrezime= new TableColumn<>("Prezime");
        TableColumn<Psychotherapist, String> tcPsihijatarJmbg = new TableColumn<>("JMBG");
        TableColumn<Psychotherapist, LocalDate> tcPsihijatarDatumRodjenja = new TableColumn<>("Datum rodjenja");
        TableColumn<Psychotherapist, String> tcPsihijatarPrebivaliste = new TableColumn<>("Prebivaliste");
        TableColumn<Psychotherapist, String> tcPsihijatarBrojTelefona= new TableColumn<>("Broj telefona");
        TableColumn<Psychotherapist, String> tcPsihijatarEmail = new TableColumn<>("Email");
        TableColumn<Psychotherapist, LocalDate> tcPsihijatarDatumSertifikacije = new TableColumn<>("Datum sertifikacije");
        TableColumn<Psychotherapist, Integer> tcLozinka= new TableColumn<>("Lozinka:");
        TableColumn<Psychotherapist, Integer> tcPsihijatarOblastId= new TableColumn<>("Oblast ID");
        TableColumn<Psychotherapist, Integer> tcPsihijatarCentarId= new TableColumn<>("Centar ID");

        tcPsihijatarId.setCellValueFactory(new PropertyValueFactory<>("psihijatarId"));
        tcPsihijatarIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        tcPsihijatarPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        tcPsihijatarJmbg.setCellValueFactory(new PropertyValueFactory<>("jmbg"));
        tcPsihijatarDatumRodjenja.setCellValueFactory(new PropertyValueFactory<>("datumRodjenja"));
        tcPsihijatarPrebivaliste.setCellValueFactory(new PropertyValueFactory<>("prebivaliste"));
        tcPsihijatarBrojTelefona.setCellValueFactory(new PropertyValueFactory<>("brojTelefona"));
        tcPsihijatarEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcPsihijatarDatumSertifikacije.setCellValueFactory(new PropertyValueFactory<>("datumSertifikacije"));
        tcLozinka.setCellValueFactory(new PropertyValueFactory<>("lozinka"));
        tcPsihijatarOblastId.setCellValueFactory(new PropertyValueFactory<>("oblast_psihoterapije_oblast_id"));
        tcPsihijatarCentarId.setCellValueFactory(new PropertyValueFactory<>("centar_za_obuku_centar_id"));

        super.getColumns().add(tcPsihijatarId);
        super.getColumns().add(tcPsihijatarIme);
        super.getColumns().add(tcPsihijatarPrezime);
        super.getColumns().add(tcPsihijatarJmbg);
        super.getColumns().add(tcPsihijatarDatumRodjenja);
        super.getColumns().add(tcPsihijatarPrebivaliste);
        super.getColumns().add(tcPsihijatarBrojTelefona);
        super.getColumns().add(tcPsihijatarEmail);
        super.getColumns().add(tcPsihijatarDatumSertifikacije);
        super.getColumns().add(tcLozinka);
        super.getColumns().add(tcPsihijatarOblastId);
        super.getColumns().add(tcPsihijatarCentarId);
    }
}
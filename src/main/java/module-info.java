module artikli {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires jdk.compiler;
    requires java.sql;
    requires jdk.incubator.vector;
    requires mysql.connector.j;
    requires java.desktop;

    // Otvori paket novi_pocetak.model za pristup od strane JavaFX (i refleksije)
    opens novi_pocetak.model to javafx.base;
    // Izvezi paket novi_pocetak za spoljne module
    exports novi_pocetak;
}
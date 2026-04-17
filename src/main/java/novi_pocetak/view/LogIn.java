package novi_pocetak.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import novi_pocetak.controller.LogInPs;

public class LogIn extends VBox {
    private Button btnPovratak=new Button("Povratak");
    private Button btnPrijavi=new Button("Prijavi");
    private TextField tfEmail= new TextField();
    private TextField tfLozinka= new TextField();
    private Label lblNaslov=new Label("PRIJAVI PSIHOTERAPEUTA");

    public LogIn(Stage primaryStage, Stage drugiStage) {
        setAlignment(Pos.CENTER);
        setSpacing(20);
        drugiStage.setTitle("Log in");
        setMinWidth(400);
        setPadding(new Insets(10,5,10,5));
        lblNaslov.setFont(new Font("Arial", 20));
        getChildren().add(lblNaslov);
        GridPane gp1=new GridPane();
        gp1.setAlignment(Pos.CENTER);
        gp1.setHgap(10);
        gp1.setVgap(10);
        gp1.add(new Label("Email"), 0, 0);
        gp1.add(tfEmail, 1, 0);
        gp1.add(new Label("Lozinka"), 0, 1);
        gp1.add(tfLozinka, 1, 1);
        getChildren().add(gp1);
        HBox hb=new HBox();
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        setPadding(new Insets(10,5,10,5));
        hb.getChildren().add(btnPovratak);
        hb.getChildren().add(btnPrijavi);
        getChildren().addAll(hb);
        btnPovratak.setOnAction(e  -> {
            drugiStage.close();
            primaryStage.show();
        });

        btnPrijavi.setOnAction(e  -> {
            if(tfEmail.getText().isEmpty() || tfLozinka.getText().isEmpty())
                return;
            LogInPs lg=new LogInPs(tfEmail,tfLozinka);
            lg.handle(e);

            if(lg.enableLogin()){
                Stage s4= new Stage();
                GlavniView root=new GlavniView(s4);
                s4.setScene(new Scene(root));
                s4.show();
                drugiStage.close();
            }
        });
    }
}

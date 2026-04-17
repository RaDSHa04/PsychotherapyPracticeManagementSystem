package novi_pocetak.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import novi_pocetak.model.base.Server;

public class MainView extends VBox {
    private Stage primaryStage;
    private final TableView tvPsychotherapist= new PsychotherapistTable(Server.getInstance().getPsychotherapists());
    private final Button btnSignUp= new Button("Sign up");
    private final Button btnLogIn= new Button("Log in");


    public MainView(Stage primaryStage) {
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setMinWidth(1043);
        setPadding(new Insets(20,0,0,0));
        primaryStage.setTitle("Informacije o prihoterapeutu");
        primaryStage.setResizable(false);
        this.primaryStage=primaryStage;
        napraviIzgled();

        btnSignUp.setOnAction(e -> {
            Stage s2= new Stage();
            SingUp root= new SingUp(primaryStage, s2);
            s2.setScene(new Scene(root));
            s2.show();
            primaryStage.close();
        });

        btnLogIn.setOnAction(e -> {
            Stage s3= new Stage();
            LogIn root= new LogIn(primaryStage, s3);
            s3.setScene(new Scene(root));
            s3.show();
            primaryStage.close();
        });
    }

    private void napraviIzgled(){
        GridPane gp1=new GridPane();
        gp1.setAlignment(Pos.CENTER);
        gp1.setHgap(10);
        gp1.setVgap(10);
        gp1.setPadding(new Insets(10,10,10,10));
        gp1.add(btnSignUp,0,0);
        gp1.add(btnLogIn,1,0);
        getChildren().add(gp1);
        getChildren().add(tvPsychotherapist);
    }
}

package novi_pocetak;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import novi_pocetak.model.utility.JDBCUtils;
import novi_pocetak.view.MainView;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        JDBCUtils.connect();
        Scene sc=new Scene(new MainView(stage));
        stage.setScene(sc);
        stage.show();
    }
}
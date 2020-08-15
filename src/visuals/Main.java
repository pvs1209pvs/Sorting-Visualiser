package visuals;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage s) throws Exception {
        
        primaryStage = new Stage();

        Parent pane = FXMLLoader.load(getClass().getResource("Dialog.fxml"));
        primaryStage.setTitle("Sorting Selection");

        Scene scene = new Scene(pane, 630, 450);

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

    }
}

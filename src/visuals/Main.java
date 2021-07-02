package visuals;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage s) throws Exception {
        
        primaryStage = new Stage();

        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dialog.fxml")));
        primaryStage.setTitle("Sorting Selection");

        Scene scene = new Scene(pane, 600, 500);

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

    }
}

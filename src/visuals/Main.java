package visuals;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main extends Application {

    static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage s) throws Exception {

        List<String> list = Stream.of("param", "asma").map(String::toUpperCase).collect(Collectors.toList());
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
            String next = iterator.next();
            System.out.println(next);
        }
        
        primaryStage = new Stage();

        Parent pane = FXMLLoader.load(getClass().getResource("Dialog.fxml"));
        primaryStage.setTitle("Sorting Selection");

        Scene scene = new Scene(pane, 630, 450);

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

    }
}

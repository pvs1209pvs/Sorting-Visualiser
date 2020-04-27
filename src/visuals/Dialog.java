package visuals;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//To check if the values are working click sort and it will call the corresponding methods

public class Dialog extends Application {

    static Stage primaryStage;
    private int gap = 20;
    private int width = 10;
    private double seconds = 0.07;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage s) throws Exception {

//        Bar[] bars = new Bar[10];
//        for (int i = 0; i < bars.length; i++) {
//            bars[i] = new Bar(i * gap, 50, width, ((int) (Math.random() * 15 + 1))*8);
//        }
//
//        AnimationSequence animationSequence = new AnimationSequence(bars,gap, seconds);
//
//        Stage myStage = new Stage();
//        myStage.setWidth(700);
//        myStage.setHeight(300);
//
//        Pane p = new Pane();
//        for (int i = 0; i < bars.length; i++) {
//            p.getChildren().add(bars[i]);
//        }
//
//        animationSequence.getSequenceTransition().play();
//
//        Scene myScene = new Scene(p);
//        myStage.setScene(myScene);
        //myStage.show();


        primaryStage = new Stage();

        Parent pane = FXMLLoader.load(getClass().getResource("Dialog.fxml"));
        primaryStage.setTitle("Sorting Selection");

        Scene scene = new Scene(pane, 630, 450);

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
}

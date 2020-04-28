package visuals;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static visuals.Main.primaryStage;

public class Controller {

    private AnimationSequence animationSequence;

    @FXML
    ComboBox<String> sortSelect;

    @FXML
    TextField samples;

    @FXML
    TextField minValue;

    @FXML
    TextField maxValue;

    @FXML
    ToggleGroup customInputOptions;

    @FXML
    Button fileSelect;

    @FXML
    TextField numbers;

    @FXML
    Slider speedSlider;


    @FXML
    public void initialize() {
        (customInputOptions.getToggles().get(0)).setSelected(true);
        userInput();
    }


    @FXML
    public void play() {
        System.out.println(sortSelect.getValue().toUpperCase());
        int gap = 20;
        int width = 10;

        int n = getSampleSize();
        Bar[] bars = new Bar[n];

        for (int i = 0; i < bars.length; i++) {
            bars[i] = new Bar(i * gap, 50, width, (Math.random()*maxValue()+minValue())*15);
        }

        animationSequence = new AnimationSequence();
        animationSequence.getSequenceTransition(sortSelect.getValue().toUpperCase(), bars, gap, 1 / getSpeed()).play();

        Pane p = new Pane();
        for (Bar bar : bars) {
            p.getChildren().add(bar);
        }

        Button playPause = new Button("Play/Pause");
        //playPause.setOnAction(event -> animationSequence.getSequenceTransition().pause());

        VBox vBox = new VBox(p, playPause);

        Stage myStage = new Stage();

        myStage.setWidth(1280);
        myStage.setHeight(480);

        Scene myScene = new Scene(vBox);

        myStage.setScene(myScene);
        myStage.show();


    }

    @FXML
    private void stop() {
    }

    public void Sort() {

    }

    @FXML
    private void sortIt() {

    }

    private void fileSelect() {
        FileChooser fileChooser = new FileChooser();
        File inputFile = fileChooser.showOpenDialog(primaryStage);

        Integer[] numArray = sortIt(inputFile);

        System.out.println(Arrays.toString(numArray));
    }

    private Integer[] sortIt(File inputFile) {
        try {

            Scanner scanner = new Scanner(inputFile);
            scanner.useDelimiter(" ");

            List<Integer> nums = new ArrayList<>();
            while (scanner.hasNext()) {
                nums.add(Integer.parseInt(scanner.next().trim()));
            }

            scanner.close();

            return nums.toArray(new Integer[0]);


        }
        catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        return null;
    }


    private int minValue() {
        return Integer.parseInt(minValue.getText());
    }

    private int maxValue() {
        return Integer.parseInt(maxValue.getText());
    }

    @FXML
    private int getSampleSize() {
        return Integer.parseInt(samples.getText());
    }

    @FXML
    private void userInput() {

        String id = ((RadioButton) customInputOptions.getSelectedToggle()).getId();

        switch (id) {
            case "fromRandom":
                samples.setDisable(false);
                minValue.setDisable(false);
                maxValue.setDisable(false);
                numbers.setDisable(true);
                fileSelect.setVisible(false);
                break;
            case "fromArray":
                samples.setDisable(true);
                minValue.setDisable(true);
                maxValue.setDisable(true);
                numbers.setDisable(false);
                numbers.setEditable(true);
                fileSelect.setVisible(true);
                break;

        }

    }


    private double getSpeed() {
        return speedSlider.getValue();
    }

    public void close() {
        System.exit(0);
    }
}

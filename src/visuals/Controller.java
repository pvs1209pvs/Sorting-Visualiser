package visuals;

import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


import static visuals.Dialog.primaryStage;

public class Controller {

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

        Bar[] bars = new Bar[getSampleSize()];
        for (int i = 0; i < bars.length; i++) {
            bars[i] = new Bar(i * 20, 50, 10, ((int) (Math.random() * maxValue() + minValue())) * 8);
        }

        AnimationSequence animationSequence = new AnimationSequence(bars, 20, 0.01);
        animationSequence.getSequenceTransition().play();

        Pane p = new Pane();
        for (int i = 0; i < bars.length; i++) {
            p.getChildren().add(bars[i]);
        }

        Stage myStage = new Stage();
        myStage.setWidth(700);
        myStage.setHeight(300);

        Scene myScene = new Scene(p);
        myStage.setScene(myScene);
        myStage.show();

    }


    public void Sort() {
//        System.out.println("User Parameters");
//        selectedSort();
//        getSampleSize();
//        range();
//        getSpeed();


    }

    @FXML
    private void sortit() {

    }

    private void fileselect() {
        FileChooser fileChooser = new FileChooser();
        File inputFile = fileChooser.showOpenDialog(primaryStage);

        Integer[] numArray = sortit(inputFile);

        System.out.println(Arrays.toString(numArray));
    }

    private Integer[] sortit(File inputFile) {
        try {

            Scanner scanner = new Scanner(inputFile);
            scanner.useDelimiter(" ");

            List<Integer> nums = new ArrayList<>();
            while (scanner.hasNext()) {
                nums.add(Integer.parseInt(scanner.next().trim()));
            }

            scanner.close();

            return nums.toArray(new Integer[0]);


        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        return null;
    }

    public void selectedSort() {
        System.out.println(sortSelect.getValue());
    }

    private void range() {

        System.out.println( minValue.getText()+ " to " + maxValue.getText());
    }

    private int minValue(){
        return Integer.parseInt(minValue.getText());
    }

    private int maxValue(){
        return Integer.parseInt(maxValue.getText());
    }

    @FXML
    int getSampleSize() {
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
                fileSelect.setVisible(true);
                break;

        }

    }


    private void getSpeed() {
        int value = (int) speedSlider.getValue();
        System.out.println(value);
    }

    public void close() {
        System.exit(0);
    }
}

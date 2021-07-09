package visuals;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sortingalgorithms.SorterFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

import static visuals.Main.primaryStage;

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
        customInputOptions.getToggles().get(0).setSelected(true);
        fileSelect.setVisible(true);
        userInputOption();
    }

    @FXML
    public void playAnim() {

        final int GAP = 4;
        final int WIDTH = 3;
        final int HEIGHT_SCALING = 5;

        Bar[] bars = new Bar[0];

        switch (userInputOption()) {

            case "fromRandom": {

                bars = new Bar[getSampleSize()];

                Arrays.setAll(bars, i -> new Bar(i * GAP, 25, WIDTH, (int) (Math.random() * maxValue() + minValue()) * HEIGHT_SCALING));

                break;
            }
            case "fromArray": {

                Integer[] nums = userEnteredArray();
                bars = new Bar[nums.length];

                Arrays.setAll(bars, i -> new Bar(i * GAP, 25, WIDTH, nums[i] * HEIGHT_SCALING));

                break;
            }
            case "fromFile": {

                Integer[] nums = openFile();
                bars = new Bar[nums.length];

                Arrays.setAll(bars, i -> new Bar(i * GAP, 25, WIDTH, nums[i] * HEIGHT_SCALING));

                break;
            }
        }

        if (bars.length <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty array cannot be visualised");
            alert.showAndWait();
        } else if (minValue() <= 0 || maxValue() <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter positive numbers.");
            alert.showAndWait();
        } else {
            new AnimationSequence().getSequenceTransition(
                    Stream.of(SorterFactory.ALGORITHMS.values()).filter(x -> x.toString().equals(sortSelect.getValue().toUpperCase())).findFirst().get(),
                    bars,
                    GAP,
                    1 / getSpeed()).play();

            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: white");

            Stage sortingAnimPane = new Stage();
            sortingAnimPane.setWidth(1200);
            sortingAnimPane.setHeight(900);


            pane.getChildren().add(new Label(" " + sortSelect.getValue() + " Sort"));

            for (Bar bar : bars) {
                pane.getChildren().add(bar);
            }

            Scene myScene = new Scene(pane);
            sortingAnimPane.setScene(myScene);
            sortingAnimPane.show();
        }


    }

    public Integer[] openFile() {
        numbers.setDisable(false);
        return readFromFile(new FileChooser().showOpenDialog(primaryStage));
    }

    private Integer[] readFromFile(File inputFile) {

        if (inputFile == null) {
            return new Integer[0];
        }

        try (Scanner scanner = new Scanner(inputFile)) {

            scanner.useDelimiter(" ");

            List<Integer> nums = new ArrayList<>();

            while (scanner.hasNext()) {
                nums.add(Integer.parseInt(scanner.next().trim()));
            }

            return nums.toArray(new Integer[0]);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new Integer[0];
    }

    private Integer[] userEnteredArray() {

        try {
            return Arrays.stream(numbers.getText().split(" "))
                    .map(Objects::toString)
                    .map(Integer::valueOf)
                    .toArray(Integer[]::new);
        } catch (NumberFormatException numberFormatException) {
            return new Integer[0];
        }

    }

    @FXML
    private String userInputOption() {

        String id = ((RadioButton) customInputOptions.getSelectedToggle()).getId();

        switch (id) {
            case "fromRandom":
                samples.setDisable(false);
                minValue.setDisable(false);
                maxValue.setDisable(false);
                numbers.setDisable(true);
                fileSelect.setDisable(true);
                break;

            case "fromArray":
                samples.setDisable(true);
                minValue.setDisable(true);
                maxValue.setDisable(true);
                numbers.setDisable(false);
                numbers.setEditable(true);
                fileSelect.setDisable(true);
                break;

            case "fromFile":
                samples.setDisable(true);
                minValue.setDisable(true);
                maxValue.setDisable(true);
                numbers.setDisable(false);
                numbers.setEditable(true);
                fileSelect.setDisable(false);
                break;

        }

        return id;

    }

    private int minValue() {

        try {
            return Integer.parseInt(minValue.getText());
        } catch (NumberFormatException numberFormatException) {
            return -1;
        }

    }

    private int maxValue() {

        try {
            return Integer.parseInt(maxValue.getText());
        } catch (NumberFormatException numberFormatException) {
            return -1;
        }
    }

    @FXML
    private int getSampleSize() {
        return Integer.parseInt(samples.getText());
    }

    private double getSpeed() {
        return speedSlider.getValue();
    }

    @FXML
    public void close() {
        System.exit(0);
    }


}

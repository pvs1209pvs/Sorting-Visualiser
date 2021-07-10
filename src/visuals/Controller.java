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

    final int GAP = 5;
    final int WIDTH = 4;
    final int HEIGHT_SCALING = 5;

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
        getUserInputOption();
    }


    /**
     * Make the bars that are to sorted from the input integer array.
     *
     * @return Bars array that are sorted.
     */
    private Bar[] makeBars() {

        Integer[] input = new Integer[getSampleSize()];

        switch (getUserInputOption()) {

            case "fromRandom":
                input = Arrays.stream(input).map(value -> (int) (Math.random() * getMaxValue() + getMinValue())).toArray(Integer[]::new);
                break;

            case "fromArray":
                input = userEnteredArray();
                break;

            case "fromFile":
                input = readFromFile();
                break;

        }

        Bar[] bars = new Bar[getSampleSize()];

        for (int i = 0; i < bars.length; i++) {
            bars[i] = new Bar((i + 1) * GAP, 25, WIDTH, input[i] * HEIGHT_SCALING);
        }

        return bars;

    }

    /**
     * Plays the visualization.
     */
    @FXML
    public void playAnim() {

        Bar[] bars = makeBars();

        if (bars.length <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty array cannot be visualised");
            alert.showAndWait();
        } else if (getMinValue() <= 0 || getMaxValue() <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter positive numbers.");
            alert.showAndWait();
        } else {
            new AnimationSequence().getSequenceTransition(
                    Stream.of(SorterFactory.ALGORITHMS.values()).filter(algo -> algo.toString().equals(sortSelect.getValue().toUpperCase())).findFirst().get(),
                    bars,
                    GAP,
                    1 / getSpeed()).play();

            showSortingVisualizationPane(bars);
        }

    }

    /**
     * Display the sorting visualization screen.
     *
     * @param bars Input bars array.
     */
    private void showSortingVisualizationPane(Bar[] bars) {

        Stage sortingAnimPane = new Stage();
        sortingAnimPane.setWidth((bars.length < 100) ? 470 : (bars.length * 5.11));
        sortingAnimPane.setHeight((getMaxValue() <= 40) ? 330 : getMaxValue() * 8.2);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: white");

        pane.getChildren().add(new Label(" " + sortSelect.getValue() + " Sort"));

        for (Bar bar : bars) {
            pane.getChildren().add(bar);
        }

        Scene myScene = new Scene(pane);
        sortingAnimPane.setScene(myScene);
        sortingAnimPane.show();

    }

    /**
     * Reads numbers from a text file.
     *
     * @return Integer array of number to be sorted. Integer array of size zero is returned if failed to open the file.
     */
    private Integer[] readFromFile() {

        File inputFile = new FileChooser().showOpenDialog(primaryStage);

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

    /**
     * Returns the number entered by the user.
     *
     * @return Integer array of numbers entered by the user. Integer array of size zero is returned if no numbers were
     * entered by the user.
     */
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

    /**
     * Gets user method of input.
     *
     * @return Input method selected by the user.
     */
    @FXML
    private String getUserInputOption() {

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

    /**
     * Minimum value to be in the input array.
     *
     * @return Minimum value in the array. Returns -1 if non-positive number was entered.
     */
    private int getMinValue() {

        try {
            return Integer.parseInt(minValue.getText());
        } catch (NumberFormatException numberFormatException) {
            return -1;
        }

    }

    /**
     * Maximum value to be in the input array.
     *
     * @return Maximum value in the array. Returns -1 if non-positive number was entered.
     */
    private int getMaxValue() {

        try {
            return Integer.parseInt(maxValue.getText());
        } catch (NumberFormatException numberFormatException) {
            return -1;
        }
    }

    /**
     * Number of values in the input array.
     *
     * @return Size of the input array.
     */
    @FXML
    private int getSampleSize() {
        return Integer.parseInt(samples.getText());
    }

    /**
     * Sorting speed.
     *
     * @return Sorting speed.
     */
    private double getSpeed() {
        return speedSlider.getValue();
    }

    /**
     * Closes the application with exit code of zero.
     */
    @FXML
    public void close() {
        System.exit(0);
    }


}

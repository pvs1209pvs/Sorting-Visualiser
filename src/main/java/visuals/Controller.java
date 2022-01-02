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
import java.util.stream.IntStream;
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

        int[] input = new int[0];

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setGraphic(null);

        switch (getUserInputOption()) {

            case "fromRandom":
                try{
                    input = IntStream
                            .range(0, getSampleSize())
                            .map(value -> (int) (Math.random() * getMaxValue() + getMinValue()))
                            .toArray();
                } catch (NumberFormatException numberFormatException){
                    alert.setContentText("Please enter a valid positive integer.");
                }
                break;

            case "fromArray":
                try {
                    input = userEnteredArray();
                } catch (NumberFormatException numberFormatException) {
                    input = new int[0];
                    alert.setContentText("Please enter integers only.");
                    alert.showAndWait();
                } catch (NullPointerException nullPointerException) {
                    input = new int[0];
                    alert.setContentText("Input array must contain at least one value.");
                    alert.showAndWait();
                }
                break;

            case "fromFile":
                try {
                    input = readFromFile();
                } catch (FileNotFoundException fileNotFoundException) {
                    input = new int[0];
                    alert.setContentText("Please select a valid text file with space separated integers.");
                    alert.showAndWait();
                } catch (NullPointerException nullPointerException) {
                    input = new int[0];
                    alert.setContentText("Input file must contain at least one value.");
                    alert.showAndWait();
                }
                break;


        }

        Bar[] bars = new Bar[input.length];

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

        if (bars.length > 0) {
            showSortingVisualizationPane(bars);

            new AnimationSequence().getSequenceTransition(
                    Stream.of(SorterFactory.ALGORITHMS.values()).filter(algo -> algo.toString().equals(sortSelect.getValue().toUpperCase())).findFirst().get(),
                    bars,
                    GAP,
                    1 / getSpeed()).play();
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

        Arrays.stream(bars).forEach(pane.getChildren()::add);

        Scene myScene = new Scene(pane);
        sortingAnimPane.setScene(myScene);
        sortingAnimPane.show();

    }

    /**
     * Reads numbers from a text file.
     *
     * @return Integer array of number to be sorted. Integer array of size zero is returned if failed to open the file.
     */
    private int[] readFromFile() throws FileNotFoundException {

        File inputFile = new FileChooser().showOpenDialog(primaryStage);

        if (inputFile == null) {
            throw new FileNotFoundException();
        }

        Scanner scanner = new Scanner(inputFile);

        scanner.useDelimiter(" ");

        List<Integer> nums = new ArrayList<>();

        while (scanner.hasNext()) {
            nums.add(Integer.parseInt(scanner.next().trim()));
        }

        scanner.close();

        int[] inputArray = nums.stream().mapToInt(value -> value).toArray();

        if (inputArray.length <= 0) {
            throw new NullPointerException();
        }

        return inputArray;

    }

    /**
     * Returns the number entered by the user.
     *
     * @return Integer array of numbers entered by the user. Integer array of size zero is returned if no numbers were
     * entered by the user.
     */
    private int[] userEnteredArray() throws NullPointerException {

        String userInputText = numbers.getText();

        if (userInputText.isEmpty()) {
            throw new NullPointerException();
        }

        return Arrays.stream(userInputText.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

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
    private int getMinValue() throws NumberFormatException {

        int min = Integer.parseInt(minValue.getText());

        if (min <= 0) {
            throw new NumberFormatException();
        }

        return min;

    }

    /**
     * Maximum value to be in the input array.
     *
     * @return Maximum value in the array. Returns -1 if non-positive number was entered.
     */
    private int getMaxValue() throws NumberFormatException {

        int max = Integer.parseInt(maxValue.getText());

        if (max <= 0) {
            throw new NumberFormatException();
        }

        return max;

    }

    /**
     * Number of values in the input array.
     *
     * @return Size of the input array.
     */
    @FXML
    private int getSampleSize() {

       return  Integer.parseInt(samples.getText());

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

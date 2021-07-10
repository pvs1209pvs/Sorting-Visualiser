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
        getUserInputOption();
    }

    /**
     * Plays the visualization.
     */
    @FXML
    public void playAnim() {

        final int GAP = 5;
        final int WIDTH = 4;
        final int HEIGHT_SCALING = 5;

        Bar[] bars = new Bar[0];

        switch (getUserInputOption()) {

            case "fromRandom": {

                bars = new Bar[getSampleSize()];

                Arrays.setAll(bars, i -> new Bar((i + 1) * GAP, 25, WIDTH, (int) (Math.random() * getMaxValue() + getMinValue()) * HEIGHT_SCALING));

                break;
            }
            case "fromArray": {

                Integer[] nums = userEnteredArray();
                bars = new Bar[nums.length];

                Arrays.setAll(bars, i -> new Bar((i + 1) * GAP, 25, WIDTH, nums[i] * HEIGHT_SCALING));

                break;
            }
            case "fromFile": {

                Integer[] nums = readFromFile();
                bars = new Bar[nums.length];

                Arrays.setAll(bars, i -> new Bar((i + 1) * GAP, 25, WIDTH, nums[i] * HEIGHT_SCALING));

                break;
            }
        }

        if (bars.length <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty array cannot be visualised");
            alert.showAndWait();
        } else if (getMinValue() <= 0 || getMaxValue() <= 0) {
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
            sortingAnimPane.setWidth((bars.length < 100) ? 470 : (bars.length * 5.11));
            sortingAnimPane.setHeight((getMaxValue() <= 40) ? 330 : getMaxValue() * 8.2);

            pane.getChildren().add(new Label(" " + sortSelect.getValue() + " Sort"));

            for (Bar bar : bars) {
                pane.getChildren().add(bar);
            }

            Scene myScene = new Scene(pane);
            sortingAnimPane.setScene(myScene);
            sortingAnimPane.show();
        }


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

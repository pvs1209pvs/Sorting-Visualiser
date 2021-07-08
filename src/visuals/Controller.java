package visuals;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Paint;
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
        (customInputOptions.getToggles().get(0)).setSelected(true);
        fileSelect.setVisible(true);
        userInputOption();
    }

    @FXML
    public void playAnim() {

        final int GAP = 20;
        final int WIDTH = 10;

        int n = 0;
        Bar[] bars = new Bar[n];

        switch (userInputOption()) {

            case "fromRandom": {

                n = getSampleSize();
                bars = new Bar[n];

                Arrays.setAll(bars, i -> new Bar(i * GAP + WIDTH, WIDTH, WIDTH, (int) (Math.random() * maxValue() + minValue()) * 15));

                break;
            }
            case "fromArray": {

                Integer[] nums = userEnteredArray();
                n = nums.length;
                bars = new Bar[n];

                Arrays.setAll(bars, i -> new Bar(i * GAP, 5, WIDTH, nums[i] * 15));

                break;
            }
            case "fromFile": {

                Integer[] nums = openFile();
                n = nums.length;
                bars = new Bar[n];

                Arrays.setAll(bars, i -> new Bar(i * GAP, 5, WIDTH, nums[i] * 15));

            }
        }

        new AnimationSequence().getSequenceTransition(
                Stream.of(SorterFactory.ALGORITHMS.values()).filter(x -> x.toString().equals(sortSelect.getValue().toUpperCase())).findFirst().get(),
                bars,
                GAP,
                1 / getSpeed()).play();

        Pane pane = new Pane();

        VBox vBox = new VBox(pane);
        vBox.setStyle("-fx-background-color: black");

        Stage myStage = new Stage();

        myStage.setWidth((GAP) * n - WIDTH + (2 * WIDTH));
        myStage.setHeight((int) Math.ceil(Collections.max(Arrays.asList(bars)).getHeight()) + (5 * WIDTH));

        pane.setStyle("-fx-background-color: black");

        for (Bar bar : bars) {
            bar.setFill(Paint.valueOf("#000000"));
            bar.setStroke(Paint.valueOf("#ffffff"));
            pane.getChildren().add(bar);
        }


        Scene myScene = new Scene(vBox);
        myStage.setScene(myScene);
        myStage.show();


    }

    public Integer[] openFile() {

        numbers.setDisable(true);
        return readFromFile(new FileChooser().showOpenDialog(primaryStage));

    }

    private Integer[] readFromFile(File inputFile) {

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

        return null;
    }

    private Integer[] userEnteredArray() {
        return Arrays.stream(numbers.getText().split(" "))
                .map(Objects::toString)
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
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
        return Integer.parseInt(minValue.getText());
    }

    private int maxValue() {
        return Integer.parseInt(maxValue.getText());
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

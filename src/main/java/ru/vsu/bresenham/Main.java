package ru.vsu.bresenham;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    private static final int BRUSH_SIZE = 10;
    private static final int ROWS_COUNT = 20;
    private static final int COLS_COUNT = 10;

    private Map<List<Integer>, Integer> trainingSamples = new LinkedHashMap<>();

    @FXML
    private Canvas canvas;
    @FXML
    private Label result;
    @FXML
    private TextField trainingSamplesCountTextField;

    @FXML
    void onActionAddTrainingSample(ActionEvent event) {
        List<Integer> inputs = getInputs();

        if (inputs.stream().allMatch(v -> v.equals(0))) {
            result.setText("Error: all inputs = 0");
            return;
        }

        if (trainingSamples.isEmpty()) {
            trainingSamples.put(inputs, 1);
        } else {
            trainingSamples.put(inputs, 0);
        }
        trainingSamplesCountTextField.setText(String.valueOf(trainingSamples.size()));
        clearCanvas();
    }

    private Perceptron perceptron = new Perceptron(ROWS_COUNT * COLS_COUNT);

    @FXML
    void onActionIdentify(ActionEvent event) {
        List<Integer> inputs = getInputs();
        boolean identify = perceptron.identify(inputs);
        if (identify) {
            result.setText("Success");
            result.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        } else {
            result.setText("Failed");
            result.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
    }

    @FXML
    void onActionTrain(ActionEvent event) {
        for (int i = 0; i < 3; i++) {
            System.out.println("==iteration " + i + " ==");
            perceptron.train(trainingSamples);
            System.out.println("Trained weights: " + perceptron.getWeights());

//            digits.forEach((k, v) -> {
//                boolean b = bresenham.identify(k);
//                if (b) {
//                    System.out.println("EQUAL");
//                } else {
//                    System.out.println("NOT");
//                }
//            });
//            System.out.println("====================");
//            if (i == 25 || i == 49) {
//                System.out.println("Test samples");
//                testSamples.forEach((k, v) -> {
//                    boolean b = bresenham.identify(k);
//                    if (b) {
//                        System.out.println("EQUAL");
//                    } else {
//                        System.out.println("NOT");
//                    }
//                });
//            }
        }
    }


    public List<Integer> getInputs() {
        int height = (int) canvas.getHeight();
        int width = (int) canvas.getWidth();
        WritableImage snapshot = canvas.snapshot(null, null);
        int rowSize = height / ROWS_COUNT;
        int colSize = width / COLS_COUNT;

        List<Integer> inputs = new ArrayList<>();

        for (int i = 0; i < ROWS_COUNT; i++) {
            for (int j = 0; j < COLS_COUNT; j++) {
                int input = getInput(snapshot, i * rowSize, j * colSize, rowSize, colSize);
                inputs.add(input);
            }
        }
        return inputs;
    }

    private int getInput(WritableImage snapshot, int h, int w, int rowSize, int colSize) {
        PixelReader pixelReader = snapshot.getPixelReader();
        int iMax = h + rowSize;
        int jMax = w + colSize;
        for (int i = h; i < iMax; i++) {
            for (int j = w; j < jMax; j++) {
                if (pixelReader.getColor(j, i).equals(Color.BLACK)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    @FXML
    void onMouseDragged(MouseEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillRect(event.getX(), event.getY(), BRUSH_SIZE, BRUSH_SIZE);
    }

    @FXML
    void onActionReset(ActionEvent event) {
        clearTrainingSamples();
    }

    @FXML
    void onActionClear(ActionEvent event) {
        clearCanvas();
    }

    private void clearTrainingSamples(){
        trainingSamples.clear();
        trainingSamplesCountTextField.setText(String.valueOf(0));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void clearCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        result.setText("");
        result.setBackground(null);
    }


    @FXML
    void onActionResetAll(ActionEvent event) {
        clearCanvas();
        perceptron = new Perceptron(ROWS_COUNT * COLS_COUNT);
        clearTrainingSamples();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

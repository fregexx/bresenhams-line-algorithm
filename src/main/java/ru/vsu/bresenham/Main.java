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
import javafx.scene.image.PixelWriter;
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

    private Map<List<Integer>, Integer> trainingSamples = new LinkedHashMap<>();

    @FXML
    private Canvas canvas;

    @FXML
    void onActionDrawCircle(ActionEvent event) {
        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        PixelWriter pixelWriter = gc.getPixelWriter();

        int h = height / 2;
//        for (int i = 0; i < width; i++) {
//            pixelWriter.setColor(h, i, Color.RED);
//        }

        draw(h, h, 100);


//        clearCanvas();
    }

    public void draw(int x0, int y0, int R) {
        int x = 0;
        int y = R;

        do {
            drawPixel(x, y);
            System.out.println("(" + x + "," + y + ")");
            double deltaDiag = (x + 1) * (x + 1) + (y - 1) * (y - 1) - R * R;
            if (deltaDiag < 0) {  //(точка внутри => вправо или по диагонали)
                double deltaRight = (x + 1) * (x + 1) + y * y - R * R;
                if (Math.abs(deltaDiag) - Math.abs(deltaRight) < 0) { //диагональный пиксель ближе к окружности
                    x += 1;
                    y -= 1;
//                    drawPixel(x+1, y+1);
                } else {
                    x += 1;
//                    drawPixel(x+1, y); //горизонтальный пиксель ближе к окружности
                }
            } else { //(точка внутри => вниз или по диагонали)
                double deltaBottom = (x) * (x) + (y - 1) * (y - 1) - R * R;
                if (Math.abs(deltaDiag) - Math.abs(deltaBottom) < 0) { //диагональный пиксель ближе к окружности
                    x += 1;
                    y -= 1;
//                    drawPixel(x+1, y+1);
                } else {
                    y -= 1;
//                    drawPixel(x, y+1); //вертикальный пиксель ближе к окружности
                }
            }
//            System.out.println("(" + x + "," + y + ")");
//            drawPixel(x, y);
        } while (x <= R && y >= 0);

    }

    public void drawPixel(int x, int y) {
        draw(x, y);
        draw(-x, -y);
        draw(x, -y);
        draw(-x, y);
    }

    public void draw(int x, int y) {
        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();
        int h2 = height / 2;
        int w2 = width / 2;
        x += w2;
        y += h2;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        PixelWriter pixelWriter = gc.getPixelWriter();
        pixelWriter.setColor(x, y, Color.RED);
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package ru.vsu.bresenham;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @FXML
    private Canvas canvas;
    @FXML
    private TextField textFieldRadius;

    @FXML
    void onActionDrawCircle(ActionEvent event) {
        clearCanvas();
        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();
        int radius = Integer.valueOf(textFieldRadius.getText());
        draw(radius);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeOval(width/2-radius,height/2-radius,radius*2,radius*2);
    }

    public void draw(int R) {
        int x = 0;
        int y = R;
        do {
            drawPoints(x, y);
            System.out.println("(" + x + "," + y + ")");
            double deltaDiag = (x + 1) * (x + 1) + (y - 1) * (y - 1) - R * R;
            if (deltaDiag < 0) {  //(точка внутри => вправо или по диагонали)
                double deltaRight = (x + 1) * (x + 1) + y * y - R * R;
                if (Math.abs(deltaDiag) - Math.abs(deltaRight) < 0) { //диагональный пиксель ближе к окружности
                    x += 1;
                    y -= 1;
                } else {
                    x += 1;
                }
            } else { //(точка внутри => вниз или по диагонали)
                double deltaBottom = (x) * (x) + (y - 1) * (y - 1) - R * R;
                if (Math.abs(deltaDiag) - Math.abs(deltaBottom) < 0) { //диагональный пиксель ближе к окружности
                    x += 1;
                    y -= 1;
                } else {
                    y -= 1;
                }
            }
        } while (x <= R && y >= 0);

    }

    public void drawPoints(int x, int y) {

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

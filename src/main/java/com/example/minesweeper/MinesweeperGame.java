package com.example.minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MinesweeperGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        View view = new View();
        view.initialize();
        Model model = new Model();
        model.setOnMines();
        Controller controller = new Controller(view, model);
        controller.setOnMouseControl();
        Scene scene = new Scene(view);
        stage.setTitle("Minesweeper");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
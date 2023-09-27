package com.example.minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MinesweeperGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);
        controller.initializeNewGame();
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
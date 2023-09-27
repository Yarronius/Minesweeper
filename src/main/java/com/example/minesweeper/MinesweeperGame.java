package com.example.minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MinesweeperGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        View view = new View();

        Model model = new Model(9, 9);
        Controller controller = new Controller(view, model);
        controller.initializeNewGame(9, 9);
        Scene scene = new Scene(view);
        stage.setTitle("Minesweeper");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setX(500);
        stage.show();
        view.showDialogMenu("Выберите режим игры");
    }

    public static void main(String[] args) {
        launch();
    }
}
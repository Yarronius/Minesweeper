package com.example.minesweeper;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View extends GridPane {
    private Button newGameButton = new Button("Новая игра");
    private Button exitButton = new Button("Закрыть программу");
    private Stage stage;

    public void initialize() {
        getChildren().clear();
        getColumnConstraints().clear();
        getRowConstraints().clear();
        stage = new Stage();

        for(int i = 0; i < 9; i++) {
            ColumnConstraints col = new ColumnConstraints(50);
            RowConstraints row = new RowConstraints(50);
            getColumnConstraints().add(col);
            getRowConstraints().add(row);
        }

        for (int i = 0; i < getColumnCount(); i++) {
            for (int j = 0; j < getRowCount(); j++) {
                Rectangle rect = new Rectangle(50, 50, Color.GRAY);
                Text text = new Text("");
                text.setFont(new Font("Arial", 48));
                text.setVisible(false);
                rect.setStroke(Color.BLACK);
                text.setTranslateX(10);
                add(rect, i, j);
                add(text, i, j);
            }
        }
    }

    public void endOfGame(Tile[][] gameField, String result) {
        if(result.equals("проиграли")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if(gameField[i][j].isMined()) gameField[i][j].setOpen(true);
                }
            }
        }
        draw(gameField);
        exitButton.setAlignment(Pos.BOTTOM_CENTER);
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);
        pane.getChildren().addAll(newGameButton, exitButton);
        Scene scene = new Scene(pane, 250, 100);
        stage.setTitle("Вы " + result +"!");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void draw(Tile[][] gameField) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int index = (i * 9 + j)  * 2;
                Rectangle rect = (Rectangle) this.getChildren().get(index);
                Text text = (Text) this.getChildren().get(index + 1);
                if(gameField[i][j].isOpen()) {
                    rect.setFill(Color.ANTIQUEWHITE);
                    String neighborCount = gameField[i][j].getMinedNeighborsCount() > 0 ? String.valueOf(gameField[i][j].getMinedNeighborsCount()) : "";
                    text.setText(neighborCount);
                    text.fillProperty().setValue(Color.BLACK);
                    text.strokeProperty().setValue(null);
                    text.setVisible(true);
                }
                if(gameField[i][j].isOpen() && gameField[i][j].isMined()) {
                    text.setText("\uD83D\uDCA3");
                    text.fillProperty().setValue(Color.RED);
                    text.strokeProperty().setValue(Color.BLACK);
                    text.boundsTypeProperty().setValue(TextBoundsType.VISUAL);
                }
                if(gameField[i][j].isMarked() && !gameField[i][j].isOpen()) {
                    text.setText("\uD83D\uDEA9");
                    text.fillProperty().setValue(Color.RED);
                    text.strokeProperty().setValue(Color.BLACK);
                    text.setVisible(true);
                    text.setMouseTransparent(true);
                } else if(!gameField[i][j].isOpen()) {
                    text.setText("");
                }
            }
        }
    }

    public Button getNewGameButton() {
        return newGameButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public Stage getStage() {
        return stage;
    }
}

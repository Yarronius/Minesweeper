package com.example.minesweeper;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View extends GridPane {
    public void initialize() {
        for(int i = 0; i < 9; i++) {
            ColumnConstraints col = new ColumnConstraints(50);
            RowConstraints row = new RowConstraints(50);
            this.getColumnConstraints().add(col);
            this.getRowConstraints().add(row);
        }

        for (int i = 0; i < this.getColumnCount(); i++) {
            for (int j = 0; j < this.getRowCount(); j++) {
                Rectangle rect = new Rectangle(50, 50, Color.GRAY);
                Text text = new Text("");
                text.setFont(new Font("Arial", 48));
                text.setVisible(false);
                rect.setStroke(Color.BLACK);
                text.setTranslateX(10);
                this.add(rect, i, j);
                this.add(text, i, j);
            }
        }
    }

    public void gameOver(Tile[][] gameField) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gameField[i][j].setOpen(true);
            }
        }
        this.draw(gameField);
        Button button1 = new Button("Новая игра");
        button1.setOnMouseClicked(event -> {

        });
        Button button2 = new Button("Закрыть программу");
        button2.setAlignment(Pos.BOTTOM_CENTER);
        button2.setOnMouseClicked(event -> {System.exit(0);});
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);
        pane.getChildren().addAll(button1, button2);
        Scene scene = new Scene(pane, 250, 100);
        Stage stage = new Stage();
        stage.setTitle("Вы проиграли!");
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
}

package com.example.minesweeper;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
                this.add(rect, i, j);
                this.add(text, i, j);
            }
        }
        System.out.println(this.getChildren().size());
    }


}

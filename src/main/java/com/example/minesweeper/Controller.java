package com.example.minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Controller {
    private View view;
    private Model model;
    private boolean isGameStarted = false;

   public Controller(View view, Model model) {
       this.view = view;
       this.model = model;
   }

   public void setOnMouseControl() {
       for(int i = 0; i < 162; i+=2) {
           int x = (i/2) / 9;
           int y = (i/2) % 9;
           Rectangle rect = (Rectangle) view.getChildren().get(i);
           Text text = (Text) view.getChildren().get(i + 1);
           rect.setOnMouseClicked(event -> {
               if(!isGameStarted) {
                   while (model.getGameField()[x][y].isMined()) {
                       model.setOnMines();
                   }
                   isGameStarted = true;
               }
               rect.setFill(Color.AQUA);
               text.setVisible(true);
           });
       }
   }

}


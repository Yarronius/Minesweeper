package com.example.minesweeper;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
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
               if (event.getButton() == MouseButton.PRIMARY) {
                    /*if(model.getGameField()[x][y].isMined() && !isGameStarted) {
                        while (model.getGameField()[x][y].isMined()) {
                            model.setOnMines();
                        }
                        isGameStarted = true;
                    }*/

                    if (model.getGameField()[x][y].isMarked()) return;
                    if(model.getGameField()[x][y].isMined()) {
                        view.endOfGame(model.getGameField(), "проиграли");
                    }
                    if(model.getGameField()[x][y].getMinedNeighborsCount() == 0 && !model.getGameField()[x][y].isMarked()) {
                        model.openTileAndCheckNeighbor(x, y);

                    } else {
                        model.getGameField()[x][y].setOpen(true);
                    }
                    if (model.getMineCount() == model.closeTileCount()) {
                        view.endOfGame(model.getGameField(), "выиграли");
                    }

               } else if (event.getButton() == MouseButton.SECONDARY && !model.getGameField()[x][y].isOpen()) {
                   model.getGameField()[x][y].setMarked(!model.getGameField()[x][y].isMarked());
               }
               view.draw(model.getGameField());
           });
       }
   }

   public void setOnButtonControl() {
       view.getButton1().setOnMouseClicked(event -> {
            view.getStage().close();
           initializeNewGame();
       });

       view.getButton2().setOnMouseClicked(event -> {System.exit(0);});
   }

   public void initializeNewGame() {
       model = new Model();
       model.setOnMines();
       model.countNeighborMines();
       view.initialize();
       setOnMouseControl();
       setOnButtonControl();
   }
}


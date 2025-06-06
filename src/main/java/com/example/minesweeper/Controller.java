package com.example.minesweeper;

import javafx.scene.input.MouseButton;
import javafx.scene.shape.Rectangle;

public class Controller {
    private View view;
    private Model model;
    private boolean isGameStarted = false;

   public Controller(View view, Model model) {
       this.view = view;
       this.model = model;
   }

   public void setOnMouseControl() {
       int count = 0;
       for(int i = 0; i < 9 * 9 * 2; i+=2) {
           int x = (i/2) / 9;
           int y = (i/2) % 9;
           Tile tile = model.getGameField()[x][y];
           Rectangle rect = (Rectangle) view.getChildren().get(i);
           rect.setOnMouseClicked(event -> {
               if (event.getButton() == MouseButton.PRIMARY) {
                    if (tile.isMarked()) return;
                    if(tile.isMined() && !isGameStarted) {
                        while (tile.isMined()) {
                            model.resetTiles();
                            model.setOnMines();
                            model.countNeighborMines();
                        }
                    }
                   isGameStarted = true;

                    if(tile.isMined()) {
                        tile.setOpen(true);
                        tile.setMarked(true);
                        view.endOfGame(model.getGameField(), "Вы проиграли!");
                    }
                    if(tile.getMinedNeighborsCount() == 0 && !tile.isMined()) {
                        model.openTileAndCheckNeighbor(x, y);
                    } else {
                        tile.setOpen(true);
                    }
                    if (model.getMinesCount() == model.closedTileCount()) {
                        view.endOfGame(model.getGameField(), "Вы выиграли!");
                    }

               } else if (event.getButton() == MouseButton.SECONDARY && !tile.isOpen()) {
                   tile.setMarked(!tile.isMarked());
               }
               view.draw(model.getGameField());
           });
       }
   }

   public void setOnButtonControl() {
       view.getNewGameButton().setOnMouseClicked(event -> {
           view.getStage().close();
           initializeNewGame();
       });
       view.getExitButton().setOnMouseClicked(event -> {
           System.exit(0);
       });
   }

   public void initializeNewGame() {
       isGameStarted = false;
       model = new Model();
       model.setOnMines();
       model.countNeighborMines();
       view.initialize(model.getGameField());
       setOnMouseControl();
       setOnButtonControl();
   }
}


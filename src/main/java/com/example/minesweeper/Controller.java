package com.example.minesweeper;

import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Rectangle;
import javafx.stage.Window;


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
       for (int i = 0; i < model.getWidth(); i++) {
           int x = i;
           for (int j = 0; j < model.getHeight(); j++, count += 2) {
               int y = j;
               Tile tile = model.getGameField()[x][y];
               Rectangle rect = (Rectangle) view.getChildren().get(count);
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
                           tile.setMarked(true);
                           tile.setOpen(true);
                           model.endOfGame();
                           view.draw(model.getGameField());
                           view.showDialogMenu("Вы проиграли!");
                       }
                       if(tile.getMinedNeighborsCount() == 0 && !tile.isMined()) {
                           model.openTileAndCheckNeighbor(x, y);
                       } else {
                           tile.setOpen(true);
                       }
                       if (model.getMinesCount() == model.closedTileCount()) {
                           view.showDialogMenu("Вы выиграли!");
                       }

                   } else if (event.getButton() == MouseButton.SECONDARY && !tile.isOpen()) {
                       tile.setMarked(!tile.isMarked());
                   }
                   view.draw(model.getGameField());
               });
           }
       }
   }

   public void setOnButtonControl() {
       view.getNewGameButton().setOnMouseClicked(event -> {
           Scene scene = view.getScene();
           Window window = scene.getWindow();
           view.getStage().close();
           if(view.getAmateur().isSelected()) {
               initializeNewGame(16, 16);
               window.setHeight(520);
               window.setWidth(497);
           } else if (view.getProfessional().isSelected()) {
               initializeNewGame(30, 30);
               window.setHeight(940);
               window.setWidth(917);
           } else if (view.getNewer().isSelected()) {
               initializeNewGame(9, 9);
               window.setHeight(310);
               window.setWidth(287);
           } else if (view.getCustom().isSelected()) {
               initializeNewGame(view.getComboBoxWidth().getValue(), view.getComboBoxHeight().getValue());
               window.setHeight(view.getComboBoxHeight().getValue() * 30 + 40);
               window.setWidth(view.getComboBoxWidth().getValue() * 30 + 17);
           }
       });
       view.getCustom().selectedProperty().addListener((observable, oldValue, newValue) -> {
           view.getComboBoxHeight().disableProperty().setValue(oldValue);
           view.getComboBoxWidth().disableProperty().setValue(oldValue);
       });

       view.getExitButton().setOnMouseClicked(event -> {
           System.exit(0);
       });
   }

   public void initializeNewGame(int width, int height) {
       isGameStarted = false;
       model = new Model(width, height);
       model.setOnMines();
       model.countNeighborMines();
       view.initialize(model.getGameField());
       setOnMouseControl();
       setOnButtonControl();
   }
}


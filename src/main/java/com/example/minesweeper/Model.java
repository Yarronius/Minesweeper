package com.example.minesweeper;

import java.util.Random;

public class Model {
    private Tile[][] gameField;

    public Model() {
        gameField = new Tile[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gameField[i][j] = new Tile();
            }
        }
    }

    public void setOnMines() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gameField[i][j].setMined((int) (Math.random() * 10) == 9);
            }
        }
    }

    public Tile[][] getGameField() {
        return gameField;
    }
}
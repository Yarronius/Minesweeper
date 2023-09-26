package com.example.minesweeper;

import java.util.Random;

public class Model {
    private Tile[][] gameField;
    private int mineCount = 0;

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
                if(Math.random() > 0.9) {
                    gameField[i][j].setMined(true);
                    mineCount++;
                }
            }
        }
    }

    public Tile[][] getGameField() {
        return gameField;
    }

    public void countNeighborMines() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int count = 0;
                if(!getGameField()[i][j].isMined()) {
                    if (i - 1 >= 0) {
                        count += getGameField()[i - 1][j].isMined() ? 1 : 0;
                    }
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        count += getGameField()[i - 1][j - 1].isMined() ? 1 : 0;
                    }
                    if (i - 1 >= 0 && j + 1 < 9) {
                        count += getGameField()[i - 1][j + 1].isMined() ? 1 : 0;
                    }
                    if (i + 1 < 9) {
                        count += getGameField()[i + 1][j].isMined() ? 1 : 0;
                    }
                    if (i + 1 < 9 && j + 1 < 9) {
                        count += getGameField()[i + 1][j + 1].isMined() ? 1 : 0;
                    }
                    if (i + 1 < 9 && j - 1 >= 0) {
                        count += getGameField()[i + 1][j - 1].isMined() ? 1 : 0;
                    }
                    if (j - 1 >= 0) {
                        count += getGameField()[i][j - 1].isMined() ? 1 : 0;
                    }
                    if (j + 1 < 9) {
                        count += getGameField()[i][j + 1].isMined() ? 1 : 0;
                    }
                }
                gameField[i][j].setMinedNeighborsCount(count);
            }
        }
    }

    public void openTileAndCheckNeighbor(int i, int j) {
        gameField[i][j].setOpen(true);
            if (i - 1 >= 0 && !gameField[i - 1][j].isOpen()) {
                gameField[i - 1][j].setOpen(true);
                if(gameField[i - 1][j].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i - 1, j);
            }
            if (i - 1 >= 0 && j - 1 >= 0 && !gameField[i - 1][j - 1].isOpen()) {
                gameField[i - 1][j - 1].setOpen(true);
                if(gameField[i - 1][j - 1].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i - 1, j - 1);
            }
            if (i - 1 >= 0 && j + 1 < 9 && !gameField[i - 1][j + 1].isOpen()) {
                gameField[i - 1][j + 1].setOpen(true);
                if(gameField[i - 1][j + 1].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i - 1, j + 1);
            }
            if (i + 1 < 9 && !gameField[i + 1][j].isOpen()) {
                gameField[i + 1][j].setOpen(true);
                if(gameField[i + 1][j].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i + 1, j);
            }
            if (i + 1 < 9 && j + 1 < 9 && !gameField[i + 1][j + 1].isOpen()) {
                gameField[i + 1][j + 1].setOpen(true);
                if(gameField[i + 1][j + 1].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i + 1, j + 1);
            }
            if (i + 1 < 9 && j - 1 >= 0 && !gameField[i + 1][j - 1].isOpen()) {
                gameField[i + 1][j - 1].setOpen(true);
                if(gameField[i + 1][j - 1].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i + 1, j - 1);
            }
            if (j - 1 >= 0 && !gameField[i][j - 1].isOpen()) {
                gameField[i][j - 1].setOpen(true);
                if(gameField[i][j - 1].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i , j - 1);
            }
            if (j + 1 < 9 && !gameField[i][j + 1].isOpen()) {
                gameField[i][j + 1].setOpen(true);
                if(gameField[i][j + 1].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i, j + 1);
            }
        }

        public int closeTileCount() {
        int closeTileCount = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(!gameField[i][j].isOpen()) {
                    closeTileCount++;
                }
            }
        }
        return closeTileCount;
    }

    public int getMineCount() {
        return mineCount;
    }
}

package com.example.minesweeper;


public class Model {
    private Tile[][] gameField;
    private int minesCount = 0;
    private int width;
    private int height;

    public Model(int width, int height) {
        this.width = width;
        this.height = height;
        gameField = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                gameField[i][j] = new Tile();
            }
        }
    }

    public void setOnMines() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(Math.random() > 0.9) {
                    gameField[i][j].setMined(true);
                    minesCount++;
                }
            }
        }
    }

    public void resetTiles() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                gameField[i][j].setMarked(false);
                gameField[i][j].setOpen(false);
                gameField[i][j].setMined(false);
                }
            }
        }

    public void endOfGame() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                if (gameField[i][j].isMined() && !gameField[i][j].isMarked()) gameField[i][j].setOpen(true);
            }
        }
    }

    public Tile[][] getGameField() {
        return gameField;
    }

    public void countNeighborMines() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int count = 0;
                if(!getGameField()[i][j].isMined()) {
                    if (i - 1 >= 0) {
                        count += getGameField()[i - 1][j].isMined() ? 1 : 0;
                    }
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        count += getGameField()[i - 1][j - 1].isMined() ? 1 : 0;
                    }
                    if (i - 1 >= 0 && j + 1 < height) {
                        count += getGameField()[i - 1][j + 1].isMined() ? 1 : 0;
                    }
                    if (i + 1 < width) {
                        count += getGameField()[i + 1][j].isMined() ? 1 : 0;
                    }
                    if (i + 1 < width && j + 1 < height) {
                        count += getGameField()[i + 1][j + 1].isMined() ? 1 : 0;
                    }
                    if (i + 1 < width && j - 1 >= 0) {
                        count += getGameField()[i + 1][j - 1].isMined() ? 1 : 0;
                    }
                    if (j - 1 >= 0) {
                        count += getGameField()[i][j - 1].isMined() ? 1 : 0;
                    }
                    if (j + 1 < height) {
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
            if (i - 1 >= 0 && j + 1 < height && !gameField[i - 1][j + 1].isOpen()) {
                gameField[i - 1][j + 1].setOpen(true);
                if(gameField[i - 1][j + 1].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i - 1, j + 1);
            }
            if (i + 1 < width && !gameField[i + 1][j].isOpen()) {
                gameField[i + 1][j].setOpen(true);
                if(gameField[i + 1][j].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i + 1, j);
            }
            if (i + 1 < width && j + 1 < height && !gameField[i + 1][j + 1].isOpen()) {
                gameField[i + 1][j + 1].setOpen(true);
                if(gameField[i + 1][j + 1].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i + 1, j + 1);
            }
            if (i + 1 < width && j - 1 >= 0 && !gameField[i + 1][j - 1].isOpen()) {
                gameField[i + 1][j - 1].setOpen(true);
                if(gameField[i + 1][j - 1].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i + 1, j - 1);
            }
            if (j - 1 >= 0 && !gameField[i][j - 1].isOpen()) {
                gameField[i][j - 1].setOpen(true);
                if(gameField[i][j - 1].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i , j - 1);
            }
            if (j + 1 < height && !gameField[i][j + 1].isOpen()) {
                gameField[i][j + 1].setOpen(true);
                if(gameField[i][j + 1].getMinedNeighborsCount() == 0) openTileAndCheckNeighbor(i, j + 1);
            }
        }

        public int closedTileCount() {
        int closedTileCount = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(!gameField[i][j].isOpen()) {
                    closedTileCount++;
                }
            }
        }
        return closedTileCount;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

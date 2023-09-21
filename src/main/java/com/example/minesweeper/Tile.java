package com.example.minesweeper;

public class Tile {
    private boolean isMined = false;
    private boolean isMarked = false;
    private int minedNeighborsCount = 0;

    public boolean isMined() {
        return isMined;
    }

    public void setMined(boolean mined) {
        isMined = mined;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public int getMinedNeighborsCount() {
        return minedNeighborsCount;
    }

    public void setMinedNeighborsCount(int minedNeighborsCount) {
        this.minedNeighborsCount = minedNeighborsCount;
    }
}

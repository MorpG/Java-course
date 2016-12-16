package ru.agolovin.models;


public class Cell {

    private int col;

    private int row;

    Cell(int sCol, int sRow) {
        this.col = sCol;
        this.row = sRow;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}

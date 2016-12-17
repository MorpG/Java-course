package ru.agolovin.models;

/**
 * Class for cell.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Cell {

    /**
     * column on the board.
     */
    private int col;

    /**
     * row on the board.
     */
    private int row;

    /**
     * constructor.
     * @param sCol int
     * @param sRow int
     */
    public Cell(final int sCol, final int sRow) {
        this.col = sCol;
        this.row = sRow;
    }

    /**
     * return column on the board.
     * @return column int
     */
    public final int getCol() {
        return col;
    }

    /**
     * return row on the board.
     * @return row int
     */
    public final int getRow() {
        return row;
    }
}

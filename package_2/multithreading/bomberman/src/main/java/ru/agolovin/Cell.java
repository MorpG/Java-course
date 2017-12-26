package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Cell {

    /**
     * X crd.
     */
    private int xCell;

    /**
     * Y crd.
     */
    private int yCell;

    /**
     * Marker busy cell.
     */
    private volatile boolean isStop = false;

    /**
     * Possible figure.
     */
    private Figure figure;

    /**
     * Constructor.
     *
     * @param xCell int
     * @param yCell int
     */
    Cell(int xCell, int yCell) {
        this.xCell = xCell;
        this.yCell = yCell;
    }

    /**
     * Get for isStop.
     *
     * @return boolean result
     */
    public boolean getIsStop() {
        return !this.isStop;
    }

    /**
     * Change isStop value.
     *
     * @param stop boolean
     */
    public void setIsStop(boolean stop) {
        this.isStop = stop;
    }

    /**
     * Get X crd value.
     *
     * @return int X crd.
     */
    public int getXCell() {
        return this.xCell;
    }

    /**
     * Get Y crd value.
     *
     * @return int yCell
     */
    public int getYCell() {
        return this.yCell;
    }

    /**
     * Get figure type.
     *
     * @return Figure figure
     */
    public Figure getFigure() {
        synchronized (this) {
            return figure;
        }
    }

    /**
     * Set figure type.
     *
     * @param figure Figure.
     */
    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}

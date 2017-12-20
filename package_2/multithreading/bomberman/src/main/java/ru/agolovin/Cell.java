package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Cell {

    private int xCell;
    private int yCell;
    private volatile boolean isStop = false;
    private Figure figure;

    Cell(int xCell, int yCell) {
        this.xCell = xCell;
        this.yCell = yCell;
    }

    public boolean getIsStop() {
        return this.isStop;
    }

    public void setIsStop(boolean stop) {
        this.isStop = stop;
    }

    public int getXCell() {
        return this.xCell;
    }

    public int getYCell() {
        return this.yCell;
    }

    public Figure getFigure() {
        synchronized (this) {
            return figure;
        }
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}

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

    public Cell(int xCell, int yCell) {
        this.xCell = xCell;
        this.yCell = yCell;
    }

    public boolean getIsStop() {
        return this.isStop;
    }

    public void setIsStop(boolean stop) {
        this.isStop = stop;
    }

    public int getxCell() {
        return this.xCell;
    }

    public int getyCell() {
        return this.yCell;
    }
}

package ru.agolovin;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Player implements Runnable {

    private ReentrantLock[][] board;

    private int xCell;
    private int yCell;

    private ReentrantLock lock;

    private boolean flag;

    @Override
    public void run() {

    }

    private boolean isCorrectMove(int xCell, int yCell) {
        boolean result = false;

        if ((xCell >= 0) && xCell < this.board.length
                && (yCell >= 0 && yCell < this.board.length)) {
            result = true;
        }

        return result;
    }
}

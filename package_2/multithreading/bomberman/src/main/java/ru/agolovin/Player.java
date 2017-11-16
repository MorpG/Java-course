package ru.agolovin;

import java.util.Random;
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
        this.lock.lock();
        int xNew, yNew;

        while (!flag) {
            do {
                xNew = this.xCell + new Random().nextInt(2);
                yNew = this.yCell + new Random().nextInt(2);
            } while (isCorrectMove(xNew, yNew));
        }
    }

    public boolean isStopped() {
        if (!this.flag) {
            this.flag = true;
        }
        return true;
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

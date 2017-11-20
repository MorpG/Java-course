package ru.agolovin;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Player implements Runnable {

    private final ReentrantLock[][] board;
    private final String name;

    private int xCell;
    private int yCell;
    private ReentrantLock lock;
    private boolean flag;

    Player(ReentrantLock[][] board, String name) {
        this.board = board;
        this.name = name;
    }

    private int nexIter() {
        return (new Random().nextInt(3) + (-1));
    }

    @Override
    public void run() {
        this.lock.lock();
        int xNew, yNew;

        while (!flag) {
            do {
                xNew = this.xCell + nexIter();
                yNew = this.yCell + nexIter();
            } while (!isCorrectMove(xNew, yNew));
            try {
                if (this.board[xNew][yNew].
                        tryLock(500, TimeUnit.MILLISECONDS)) {
                    this.lock.unlock();
                    this.lock = this.board[xNew][yNew];
                    this.xCell = xNew;
                    this.yCell = yNew;
                    showCurrentPosition();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    String getName() {
        return this.name;
    }

    void setPlayersPosition(ReentrantLock lock, int x, int y) {
        this.lock = lock;
        this.xCell = x;
        this.yCell = y;
    }

    private boolean isCorrectMove(int xCell, int yCell) {
        boolean result = false;

        if ((xCell >= 0) && xCell < this.board.length
                && (yCell >= 0 && yCell < this.board.length)) {
            result = true;
        }

        return result;
    }

    private void showCurrentPosition() {
        System.out.println(
                String.format(
                        "Busy cell by player %s in %d, %d",
                        this.name, this.xCell, this.yCell));
    }

    void interrupt() {
        this.flag = true;
    }
}

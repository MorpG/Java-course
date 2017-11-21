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

    /**
     * Players board.
     */
    private final ReentrantLock[][] board;

    /**
     * Player name.
     */
    private final String name;

    /**
     * coord X cell.
     */
    private int xCell;

    /**
     * coord Y cell.
     */
    private int yCell;

    /**
     * manage cell.
     */
    private ReentrantLock lock;

    /**
     * Monitore.
     */
    private boolean flag;

    /**
     * Constructor.
     *
     * @param board int
     * @param name  int
     */
    Player(ReentrantLock[][] board, String name) {
        this.board = board;
        this.name = name;
    }

    /**
     * Next player step.
     *
     * @return
     */
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

    /**
     * Get player name.
     *
     * @return String name.
     */
    String getName() {
        return this.name;
    }

    /**
     * Set player position to start.
     *
     * @param lock ReentrantLock
     * @param x    int
     * @param y    int
     */
    void setPlayersPosition(ReentrantLock lock, int x, int y) {
        this.lock = lock;
        this.xCell = x;
        this.yCell = y;
    }

    /**
     * Check fo correct move on the board.
     *
     * @param xCell int
     * @param yCell int
     * @return boolean result
     */
    private boolean isCorrectMove(int xCell, int yCell) {
        boolean result = false;

        if ((xCell >= 0) && xCell < this.board.length
                && (yCell >= 0 && yCell < this.board.length)) {
            result = true;
        }
        return result;
    }

    /**
     * Show player current position.
     */
    private void showCurrentPosition() {
        System.out.println(
                String.format(
                        "Busy cell by player %s in %d, %d",
                        this.name, this.xCell, this.yCell));
    }

    /**
     * Stop player moving.
     */
    void interrupt() {
        this.flag = true;
    }
}

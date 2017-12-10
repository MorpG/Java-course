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

    private final String name;

    Cell cell;

    private final Cell[][] board;

    public Player(String name, Cell cell, Cell[][] board) {
        this.name = name;
        this.cell = cell;
        this.board = board;
    }

    private int nexIter() {
        return (new Random().nextInt(3) + (-1));
    }

    @Override
    public void run() {

    }

    String getName() {
        return this.name;
    }

    void setPlayersPosition(ReentrantLock lock, int x, int y) {

    }

    private boolean isCorrectMove(int xCell, int yCell) {
        boolean result = false;

        if ((xCell >= 0) && xCell < this.board.length
                && (yCell >= 0 && yCell < this.board.length)) {
            result = true;
        }
        return result;
    }

//    private void showCurrentPosition() {
//        System.out.println(
//                String.format(
//                        "Busy cell by player %s in %d, %d",
//                        this.name, this.xCell, this.yCell));
//    }
//
//    void interrupt() {
//        this.flag = true;
//    }
}

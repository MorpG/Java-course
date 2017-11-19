package ru.agolovin;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Bomberman {

    private final ReentrantLock[][] board;
    private final int size;
    private final int bots;
    private final Player[] players;
    private final Boolean cellStatus[][];

    Bomberman(int size, int bots) {
        this.size = size;
        this.board = new ReentrantLock[size][size];
        this.bots = bots;
        this.players = new Player[bots];
        this.cellStatus = new Boolean[size][size];
    }

    private void prepareBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = new ReentrantLock();
                this.cellStatus[i][j] = false;
            }
        }
    }

    private void init() {

    }

    public static void main(String[] args) {
        int size = 7;
        int bots = 2;
        long time = 10_000;

        Bomberman bomb = new Bomberman(7,2);
        bomb.init();
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bomb.stop();
        }

    }

    private void stop() {
        for (Player player : this.players) {
            player.interrupt();
        }

    }
}
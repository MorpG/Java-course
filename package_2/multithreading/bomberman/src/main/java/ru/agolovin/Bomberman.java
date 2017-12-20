package ru.agolovin;

import java.util.Random;
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


    private final Boolean[][] cellStatus;

    private final int blocks;

    private Bomberman(int size, int bots, int blocks) {

        this.size = size;
        this.board = new ReentrantLock[size][size];
        this.bots = bots;
        this.players = new Player[bots];
        this.cellStatus = new Boolean[size][size];
        this.blocks = blocks;
    }


    public static void main(String[] args) {
        Bomberman bomb = new Bomberman(7, 2, 2);
        bomb.init();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            bomb.stop();
        }

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
        Random random = new Random();
        prepareBoard();
        createPlayersOnBoard();

        for (int i = 0; i < this.bots; ) {

            int xCell = random.nextInt(this.size);
            int yCell = random.nextInt(this.size);

//            if (!this.cellStatus[xCell][yCell]) {
//                this.players[i]
//                        .setPlayersPosition(this.board[xCell][yCell],
//                                xCell, yCell);
//                this.cellStatus[xCell][yCell] = true;
//                System.out.println(
//                        String.format(
//                                "Players %s start in %d, %d",
//                                this.players[i].getName(), xCell, yCell));
//                i++;
//            }
        }
        setUpBlockCell();
        createThreads();
    }

    private void setUpBlockCell() {
        int xPos, yPos;
        for (int i = 0; i < this.blocks; i++) {
            xPos = getRandomCell(this.cellStatus);
            yPos = getRandomCell(this.cellStatus);
            if (this.cellStatus[xPos][yPos].equals(false)) {
                this.cellStatus[xPos][yPos] = true;
            }
        }
    }

    private int getRandomCell(Boolean[][] cellStatus) {
        return new Random().nextInt(cellStatus.length);
    }


    private void createPlayersOnBoard() {
        for (int i = 0; i < this.players.length; i++) {
//            this.players[i] = new Player(this.board, Integer.toString(i));
        }
    }


    private void createThreads() {
        for (Player player : this.players) {
            new Thread(player).start();
        }
    }


}

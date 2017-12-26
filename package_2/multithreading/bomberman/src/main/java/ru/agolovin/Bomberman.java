package ru.agolovin;

import java.util.Random;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Bomberman {

    /**
     * Game marker.
     */
    private static volatile boolean stop;

    /**
     * Game board.
     */
    private final Cell[][] board;

    /**
     * Game board size [size][size].
     */
    private final int size;

    /**
     * Number of warriors.
     */
    private final int bots;

    /**
     * Number of blocks on board.
     */
    private final int blocks;

    /**
     * Warriors array.
     */
    private Figure[] warriors;

    /**
     * For Random number.
     */
    private Random random;

    /**
     * Player thread.
     */
    private Thread player;

    /**
     * Constructor.
     *
     * @param size   int
     * @param bots   int
     * @param blocks int
     */
    private Bomberman(int size, int bots, int blocks) {
        this.size = size;
        this.board = new Cell[size][size];
        this.bots = bots;
        this.blocks = blocks;
        stop = false;
        this.random = new Random();
    }

    /**
     * Get game marker.
     *
     * @return boolean result.
     */
    public synchronized static boolean isStop() {
        return stop;
    }

    /**
     * Change game marker.
     *
     * @param stop boolean
     */
    public static void setStop(boolean stop) {
        Bomberman.stop = stop;
    }

    /**
     * Main method.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        new Bomberman(3, 3, 1).init();
    }

    /**
     * Initialization.
     */
    private void init() {
        prepareBoard();
        setUpBlockCell();
        setUpWarriors();
        createThreads();
        try {
            player.join();
            System.out.println("Game over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prepare board.
     */
    private void prepareBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Set blocks on board.
     */
    private void setUpBlockCell() {
        int count = this.blocks;
        while (count != 0) {
            int x = getRandomNumber();
            int y = getRandomNumber();
            if (this.board[x][y].getIsStop()) {
                this.board[x][y].setIsStop(true);
                System.out.println(
                        String.format(
                                "Block setUp in %s, %s",
                                this.board[x][y].getXCell(), this.board[x][y].getYCell()));
                count--;
            }
        }
    }

    /**
     * Initialize warriors.
     */
    private void setUpWarriors() {
        this.warriors = new Warrior[this.bots];
        Warrior warrior;
        int count = 0;
        while (count != this.bots) {
            int x = getRandomNumber();
            int y = getRandomNumber();
            if (this.board[x][y].getIsStop() && this.board[x][y].getFigure() == null) {
                warrior = new Warrior(String.valueOf(count), this.board, this.board[x][y]);
                this.warriors[count] = warrior;
                count++;
            }
        }
    }

    /**
     * Prepare player figure.
     *
     * @return Player player.
     */
    private Player createBomberman() {
        Player player = null;
        boolean result = false;
        while (!result) {
            int x = getRandomNumber();
            int y = getRandomNumber();
            if (this.board[x][y].getIsStop() && this.board[x][y].getFigure() == null) {
                player = new Player("0", this.board, this.board[x][y]);
                System.out.println(
                        String.format("Bomberman %s start game in %d %d", player.type(),
                                this.board[x][y].getXCell(), this.board[x][y].getYCell())
                );
                result = true;
            }
        }
        return player;
    }

    /**
     * get random number in allowed range.
     *
     * @return int result
     */
    private int getRandomNumber() {
        return random.nextInt(this.size);
    }

    /**
     * Create threads.
     */
    private void createThreads() {
        this.player = new Thread(this.createBomberman());
        this.player.start();
        for (Figure warrior : this.warriors) {
            new Thread(warrior).start();
        }
    }
}

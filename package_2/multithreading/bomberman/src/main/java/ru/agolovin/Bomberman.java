package ru.agolovin;

import java.util.Random;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Bomberman {


    private static volatile boolean stop;
    private final Cell[][] board;
    private final int size;
    private final int bots;
    private final int blocks;
    private Figure[] warriors;
    private Random random;
    private Thread player;

    private Bomberman(int size, int bots, int blocks) {
        this.size = size;
        this.board = new Cell[size][size];
        this.bots = bots;
        this.blocks = blocks;
        stop = false;
        this.random = new Random();
    }

    public synchronized static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        Bomberman.stop = stop;
    }

    public static void main(String[] args) {
        Bomberman bomb = new Bomberman(3, 3, 1);
        bomb.init();
    }

    private void setUpMonsters() {
        this.warriors = new Warrior[this.bots];
        Warrior warrior;
        int count = 0;
        while (count != this.bots) {
            int x = getRandomNumber();
            int y = getRandomNumber();
            if (!this.board[x][y].getIsStop() && this.board[x][y].getFigure() == null) {
                warrior = new Warrior(String.valueOf(count), this.board, this.board[x][y]);
                this.warriors[count] = warrior;
                count++;
            }
        }
    }

    private void prepareBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = new Cell(i, j);
            }
        }
    }


    private void init() {
        prepareBoard();
        setUpBlockCell();
        setUpMonsters();
        createThreads();
        try {
            player.join();
            System.out.println("Game over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Player createBomberman() {
        Player player = null;
        boolean result = false;
        while (!result) {
            int x = getRandomNumber();
            int y = getRandomNumber();
            if (!this.board[x][y].getIsStop() && this.board[x][y].getFigure() == null) {
                player = new Player("0", this.board, this.board[x][y]);
                System.out.println(
                        String.format("Bomberman %s start game in %d, %d", player.id(),
                                this.board[x][y].getXCell(), this.board[x][y].getYCell())
                );
                result = true;
            }
        }
        return player;
    }


    private void setUpBlockCell() {
        int count = this.blocks;
        while (count != 0) {
            int x = getRandomNumber();
            int y = getRandomNumber();
            if (!this.board[x][y].getIsStop()) {
                this.board[x][y].setIsStop(true);
                System.out.println(
                        String.format(
                                "Block setUp in %s, %s",
                                this.board[x][y].getXCell(), this.board[x][y].getYCell()));
                count--;
            }
        }
    }

    private int getRandomNumber() {
        return random.nextInt(this.size);
    }

    private void createThreads() {
        this.player = new Thread(this.createBomberman());
        this.player.start();
        for (Figure warrior : this.warriors) {
            new Thread(warrior).start();
        }

    }
}

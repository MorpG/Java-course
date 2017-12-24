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

    private Bomberman(int size, int bots, int blocks) {

        this.size = size;
        this.board = new Cell[size][size];
        this.bots = bots;
        this.blocks = blocks;
        stop = false;
    }

    public static boolean isStop() {
        return stop;
    }

    public static synchronized void setStop(boolean stop) {
        Bomberman.stop = stop;
    }

    public static void main(String[] args) {
        Bomberman bomb = new Bomberman(7, 3, 4);
        bomb.init();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            bomb.stop();
        }

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
    }

    private Player createBomberman() {
        Player player = null;
        boolean result = false;
        while (!result) {
            int x = getRandomNumber();
            int y = getRandomNumber();
            if (!this.board[x][y].getIsStop() && this.board[x][y].getFigure() == null) {
                player = new Player("Bomberman", this.board, this.board[x][y]);
                System.out.println(
                        String.format("Bomberman %s setUp in %d, %d", player.id()
                                , this.board[x][y].getXCell(), this.board[x][y].getYCell())
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

        return (int) (Math.random() * this.size);   //rnd.nextInt(1) * this.size;
    }


    private void createThreads() {
        new Thread(this.createBomberman()).start();
        for (Figure warrior : this.warriors) {
            new Thread(warrior).start();
        }
    }


}

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
    private Random rnd;

    private Bomberman(int size, int bots, int blocks) {

        this.size = size;
        this.board = new Cell[size][size];
        this.bots = bots;
        this.blocks = blocks;
        stop = false;
        rnd = new Random();
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
        Cell cell;
        int count = 0;
        while (count != this.bots) {
            cell = getRandomCell();
            if (!cell.getIsStop() && cell.getFigure() == null) {
                warrior = new Warrior(String.valueOf(count), this.board, cell);
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
        createBomberman();
        createThreads();
        setUpMonsters();
    }

    private Player createBomberman() {
        Player player = null;
        Cell cell;
        boolean result = false;
        while (!result) {
            cell = getRandomCell();
            if (!cell.getIsStop() && cell.getFigure() == null) {
                player = new Player("Bomberman", this.board, cell);
                System.out.println(
                        String.format("Bomberman %s setUp in %d, %d", player.id()
                                , cell.getXCell(), cell.getYCell())
                );
                result = true;
            }
        }
        return player;
    }


    private void setUpBlockCell() {
        Cell cell;
        int count = this.blocks;
        while (count != 0) {
            cell = getRandomCell();

            if (!cell.getIsStop()) {
                cell.setIsStop(true);
                count--;
            }
        }
    }

    private Cell getRandomCell() {

        int xCrd;
        int yCrd;
        xCrd = rnd.nextInt() * this.size;
        yCrd = rnd.nextInt() * this.size;

        return this.board[xCrd][yCrd];
    }


    private void createThreads() {
        new Thread(this.createBomberman()).start();
        for (Figure figure : this.warriors) {
            new Thread(figure).start();
        }
    }


}

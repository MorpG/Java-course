package ru.agolovin;

import java.util.ArrayList;
import java.util.List;

abstract class Figure implements Runnable {
    final String name;
    private final Cell[][] board;
    Cell current;

    Figure(String name, Cell[][] board, Cell current) {
        this.name = name;
        this.board = board;
        this.current = current;
    }

    abstract String id();

    abstract String getName();

    List<Cell> move(Cell out) {
        List<Cell> result = new ArrayList<>();

        result.add(validateNextStep(out.getXCell() + 1, out.getYCell()));
        result.add(validateNextStep(out.getXCell() - 1, out.getYCell()));
        result.add(validateNextStep(out.getXCell(), out.getYCell() + 1));
        result.add(validateNextStep(out.getXCell(), out.getYCell() - 1));

        return result;
    }

    private Cell validateNextStep(int xCrd, int yCrd) {
        Cell result = null;
        if ((xCrd < this.board.length && xCrd >= 0)
                && (yCrd < this.board.length && yCrd >= 0)) {
            result = this.board[xCrd][yCrd];
        }
        return result;
    }

    synchronized void tryMakeStep(Cell cell) {
        this.current.setFigure(null);
        this.current = cell;
        this.current.setFigure(this);
        System.out.println(
                String.format("%s %s now in cell %d %d",
                        id(), this.getName(), this.current.getXCell(),
                        this.current.getYCell()));
    }
}

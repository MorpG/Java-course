package ru.agolovin;

import java.util.ArrayList;
import java.util.List;

abstract class Figure implements Runnable {

    /**
     * Figure name.
     */
    final String name;

    /**
     * Game board.
     */
    private final Cell[][] board;

    /**
     * current cell.
     */
    Cell current;

    /**
     * Constructor.
     *
     * @param name    String
     * @param board   Cell[][]
     * @param current Cell
     */
    Figure(String name, Cell[][] board, Cell current) {
        this.name = name;
        this.board = board;
        this.current = current;
    }

    /**
     * Method for Figure type.
     *
     * @return String value
     */
    abstract String type();

    /**
     * Method for Figure name.
     *
     * @return String name
     */
    abstract String getName();

    /**
     * Figure possible move on board.
     *
     * @param out Cell
     * @return List list
     */
    List<Cell> move(Cell out) {
        List<Cell> result = new ArrayList<>();

        result.add(validateNextStep(out.getXCell() + 1, out.getYCell()));
        result.add(validateNextStep(out.getXCell() - 1, out.getYCell()));
        result.add(validateNextStep(out.getXCell(), out.getYCell() + 1));
        result.add(validateNextStep(out.getXCell(), out.getYCell() - 1));

        return result;
    }

    /**
     * Validate figure next step.
     *
     * @param xCrd int
     * @param yCrd int
     * @return Cell cell
     */
    private Cell validateNextStep(int xCrd, int yCrd) {
        Cell result = null;
        if ((xCrd < this.board.length && xCrd >= 0)
                && (yCrd < this.board.length && yCrd >= 0)) {
            result = this.board[xCrd][yCrd];
        }
        return result;
    }

    /**
     * change figure cell on board.
     *
     * @param cell Cell
     */
    synchronized void tryMakeStep(Cell cell) {
        this.current.setFigure(null);
        this.current = cell;
        this.current.setFigure(this);
        System.out.println(
                String.format("%s %s now in cell %d %d",
                        type(), this.getName(), this.current.getXCell(),
                        this.current.getYCell()));
    }
}

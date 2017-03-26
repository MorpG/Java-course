package ru.agolovin.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Class bishop.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class BoardTest {

    /**
     * Test board.
     */
    @Test
    public final void whenTestFigureNotFoundThenResultIs() {
        final int colStart = 0;
        final int rowStart = 0;
        final int colEnd = 2;
        final int rowEnd = 0;
        boolean result = false;
        Board board = new Board();
        Cell cellStart = new Cell(colStart, rowStart);
        Cell cellEnd = new Cell(colEnd, rowEnd);
        try {
            board.move(cellStart, cellEnd);
        } catch (FigureNotFoundException e) {
            result = true;
        }
        assertThat(result, is(true));
    }

    /**
     * Test board.
     */
    @Test
    public final void whenTestMoveThenResultIs() {
        final int colStart = 0;
        final int rowStart = 0;
        final int colEnd = 1;
        final int rowEnd = 1;
        Board board = new Board();
        Cell cellStart = new Cell(colStart, rowStart);
        Cell cellEnd = new Cell(colEnd, rowEnd);
        board.setFigure(new Bishop(cellStart));
        boolean result = board.move(cellStart, cellEnd);
        assertThat(result, is(true));
    }

    /**
     * Test invalid move figure on the board.
     */
    @Test
    public final void whenInvalidMoveThenResultIs() {
        final int colStart = 0;
        final int rowStart = 0;
        final int colEnd = 2;
        final int rowEnd = 0;
        boolean result = false;
        Board board = new Board();
        Cell cellStart = new Cell(colStart, rowStart);
        Cell cellEnd = new Cell(colEnd, rowEnd);
        board.setFigure(new Bishop(cellStart));
        try {
            board.move(cellStart, cellEnd);
        } catch (ImpossibleMoveException e) {
            result = true;
        }
        assertThat(result, is(true));
    }

    /**
     * Test move figure out of the board.
     */
    @Test
    public final void whenMoveFigureOutThenResultIs() {
        final int colStart = 0;
        final int rowStart = 0;
        final int colEnd = 10;
        final int rowEnd = 10;
        boolean result = false;
        Board board = new Board();
        Cell cellStart = new Cell(colStart, rowStart);
        Cell cellEnd = new Cell(colEnd, rowEnd);
        board.setFigure(new Bishop(cellStart));
        try {
            board.move(cellStart, cellEnd);

        } catch (ImpossibleMoveException e) {
            result = true;
        }

        assertThat(result, is(true));
    }

    /**
     * Test occupiedWay.
     */
    @Test
    public final void whenOccupiedWayMoveFigureThenResultIs() {
        final int figColOne = 0;
        final int figRowOne = 0;
        final int figColTwo = 1;
        final int figRowTwo = 1;
        final int colEnd = 2;
        final int rowEnd = 2;
        boolean result = false;
        Board board = new Board();
        Cell cellFigureOne = new Cell(figColOne, figRowOne);
        Cell cellFigureTwo = new Cell(figColTwo, figRowTwo);
        Cell cellEnd = new Cell(colEnd, rowEnd);
        board.setFigure(new Bishop(cellFigureOne));
        board.setFigure(new Bishop(cellFigureTwo));
        try {
            board.move(cellFigureOne, cellEnd);
        } catch (OccupiedWayException e) {
            result = true;
        }
        assertThat(result, is(true));
    }


}

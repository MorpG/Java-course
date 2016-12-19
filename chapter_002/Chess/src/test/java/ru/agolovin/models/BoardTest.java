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

    @Test
    public final void whenTestFigureNotFoundThenresultIs() {
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
        } catch (FigureNotFoundException fne) {
            result = true;
        }
        assertThat(result, is(true));
    }

}

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
public class BishopTest {
    /**
     * Column.
     */
    private final int colStart = 2;

    /**
     * Row.
     */
    private final int rowStart = 0;

    /**
     * Test correct move Bishop figure.
     */
    @Test
    public final void whenBishopCorrectWayThenResultIs() {

        final int colEnd = 3;
        final int rowEnd = 1;

        Cell startCell = new Cell(colStart, rowStart);
        Cell endCell = new Cell(colEnd, rowEnd);
        Bishop bishop = new Bishop(startCell);
        Cell[] arr = bishop.way(endCell);
        int resCol = arr[0].getCol();
        int resRow = arr[0].getRow();
        assertThat(resCol, is(colEnd));
        assertThat(resRow, is(rowEnd));
    }

    /**
     * Test not correct move Bishop.
     */
    @Test
    public final void whenBishopNotCorrectWayThenResultIs() {
        final int colEnd = 2;
        final int rowEnd = 0;
        boolean invalid = false;
        Cell startCell = new Cell(colStart, rowStart);
        Cell endCell = new Cell(colEnd, rowEnd);
        Bishop bishop = new Bishop(startCell);
        try {
           bishop.way(endCell);
        } catch (ImpossibleMoveException nfe) {
            invalid = true;
        }
            assertThat(invalid, is(true));
    }

    /**
     * Test correct move Bishop figure.
     */
    @Test
    public final void whenBishopCorrectWayDownThenResultIs() {

        final int localStartCol = 3;
        final int localStartRow = 1;
        final int colEnd = 2;
        final int rowEnd = 0;

        Cell startCell = new Cell(localStartCol, localStartRow);
        Cell endCell = new Cell(colEnd, rowEnd);
        Bishop bishop = new Bishop(startCell);
        Cell[] arr = bishop.way(endCell);
        int resCol = arr[0].getCol();
        int resRow = arr[0].getRow();
        assertThat(resCol, is(colEnd));
        assertThat(resRow, is(rowEnd));
    }
}

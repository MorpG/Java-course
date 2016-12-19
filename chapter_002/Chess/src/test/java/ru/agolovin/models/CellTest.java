package ru.agolovin.models;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test for Class cell.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class CellTest {
    /**
     * Column.
     */
    private final int col = 3;

    /**
     * Row.
     */
    private final int row = 5;

    /**
     * Test get Column method.
     */
    @Test
    public final void whenSetColumnThenGetColIs() {

        Cell cell = new Cell(col, row);
        int result = cell.getCol();
        assertThat(result, is(col));
    }

    /**
     * Test get Row.
     */
    @Test
    public final void  whenSetRowThenGetRowIs() {
        Cell cell = new Cell(col, row);
        int result = cell.getRow();
        assertThat(result, is(row));
    }

}

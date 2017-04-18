package ru.agolovin;

import java.util.Iterator;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayIterator implements Iterator<Integer> {


    /**
     * inner array int[][].
     */
    private int[][] array;

    /**
     * Current row index.
     */
    private int rowIndex;

    /**
     * Current column index.
     */
    private int colIndex;

    /**
     * Constructor.
     *
     * @param array int[][]
     */
    ArrayIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (this.array.length > rowIndex && this.array[rowIndex].length > colIndex) {
            result = true;
        }
        return result;
    }

    @Override
    public Integer next() {
        int result;
        if (hasNext()) {
            result = this.array[rowIndex][colIndex++];
            if (colIndex > this.array[rowIndex].length - 1) {
                rowIndex++;
                colIndex = 0;
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return result;
    }
}

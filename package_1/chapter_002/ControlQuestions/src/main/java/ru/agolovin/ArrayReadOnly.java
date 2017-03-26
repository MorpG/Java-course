package ru.agolovin;

/**
 * Create Read Only array.
 */

public class ArrayReadOnly {
    /**
     * Original array.
     */
    private final int[] readOnlyArray;

    /**
     * copy of original array.
     */
    private final int[] tempReadOnlyArray;

    /**
     * Constructor.
     *
     * @param array integer array
     */
    public ArrayReadOnly(int[] array) {
        readOnlyArray = new int[array.length];
        tempReadOnlyArray = new int[array.length];
        System.arraycopy(array, 0, readOnlyArray, 0, array.length);
    }

    /**
     * get method.
     *
     * @return original array
     */
    public int[] getReadOnlyArray() {
        System.arraycopy(readOnlyArray, 0, tempReadOnlyArray, 0, readOnlyArray.length);
        return tempReadOnlyArray;
    }
}

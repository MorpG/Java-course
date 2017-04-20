package ru.agolovin;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class PrimeNumberIterator implements Iterator<Integer> {

    /**
     * Private class array.
     */
    private int[] array;

    /**
     * Index in private array.
     */
    private int index = 0;

    /**
     * Index primeNumber.
     */
    private int indexPrimeNumber;

    /**
     * Constructor.
     *
     * @param array int[]
     */
    PrimeNumberIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return indexInArray() != -1;
    }

    @Override
    public Integer next() {
        int result;
        try {
            int position = indexInArray();
            result = this.array[position];
            this.index = position + 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * Index prime number in array.
     *
     * @return index int
     */
    private int indexInArray() {
        int result = -1;
        boolean flag = false;
        for (int i = this.index; i < this.array.length; i++) {
            int element = this.array[i];
            if (element > 1) {
                if (element == 2 || element == 3) {
                    result = i;
                    break;
                } else {
                    for (int j = 2; j * j < element; j++) {
                        if (element % j == 0) {
                            break;
                        } else {
                            result = i;
                            flag = true;
                        }
                    }
                }
                if (flag) {
                    break;
                }
            }
        }
        return result;
    }
}

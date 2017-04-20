package ru.agolovin;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class EvenNumber implements Iterator<Integer> {

    /**
     * Private class array.
     */
    private int[] array;

    /**
     * Index last even number.
     */
    private int index = 0;

    /**
     * index event number.
     */
    private int indexEvNumber;

    /**
     * Constructor.
     *
     * @param array int[]
     */
    EvenNumber(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
       return indexEvenNumber() != -1;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        int result;
        if (this.indexEvNumber != -1) {
            result = this.array[this.indexEvNumber];
            this.index = this.indexEvNumber + 1;
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * Get index even number.
     *
     * @return index int
     */
    private int indexEvenNumber() {
        int result = -1;
        if (this.index < this.array.length) {
            for (int i = index; i < this.array.length; i++) {
                if (this.array[i] % 2 == 0) {
                    result = i;
                    this.indexEvNumber = i;
                    break;
                }
            }
        } else {
            indexEvNumber = -1;
        }
        return result;
    }
}

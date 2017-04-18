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
     * Constructor.
     *
     * @param array int[]
     */
    public EvenNumber(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (index < this.array.length) {
            for (int i = index; i < this.array.length; i++) {
                if (this.array[i] % 2 == 0) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        int result = 0;
        if (hasNext()) {
            for (int i = this.index; i < this.array[i]; i++) {
                if (this.array[i] % 2 == 0) {
                    result = this.array[i];
                    this.index = ++i;
                    break;
                }
            }
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }
}

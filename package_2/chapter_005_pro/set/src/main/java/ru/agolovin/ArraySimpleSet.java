package ru.agolovin;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <E> Generic.
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArraySimpleSet<E> implements Iterable<E> {

    /**
     * Array capacity.
     */
    private static final int DEFAULT_CAPACITY = 100;

    /**
     * Array.
     */
    private Object[] array;
    /**
     * Last index in array.
     */
    private int index;

    /**
     * Constructor.
     */
    ArraySimpleSet() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructor. Custom array size.
     *
     * @param size int
     */
    ArraySimpleSet(int size) {
        this.array = new Object[size];
    }

    /**
     * Get array.
     *
     * @return array
     */
    Object[] getArray() {
        return array;
    }

    /**
     * Set new array.
     *
     * @param array array
     */
    void setArray(Object[] array) {
        this.array = array;
    }

    /**
     * Get index.
     *
     * @return int index
     */
    int getIndex() {
        return index;
    }

    /**
     * Set index.
     *
     * @param index int
     */
    void setIndex(int index) {
        this.index = index;
    }

    /**
     * Set value to array item.
     *
     * @param pos   int
     * @param value Generic
     */
    void setValue(int pos, E value) {
        this.array[pos] = value;
    }

    /**
     * Rise array capacity.
     */
    void riseArray() {
        this.array = Arrays.copyOf(this.array, (this.index * 3) / 2 + 1);
    }

    /**
     * Chech if element alredy contains in array.
     *
     * @param e Generic
     * @return check result.
     */
    private boolean contains(E e) {
        boolean result = false;
        if (e != null) {
            for (Object anArray : this.array) {
                if (e.equals(anArray)) {
                    result = true;
                    break;
                }
            }

        }
        return result;
    }

    /**
     * Add element to array.
     *
     * @param e Generic
     */
    public void add(E e) {
        if (!contains(e)) {
            if (this.array.length > index) {
                this.array[index++] = e;
            } else {
                riseArray();
                this.array[index++] = e;
            }
        }
    }

    /**
     * Get element from array.
     *
     * @param index int
     * @return Element
     */
    public E get(int index) {
        Object result;
        if (index < this.index && index >= 0) {
            result = this.array[index];
        } else {
            throw new NoSuchElementException();
        }
        return (E) result;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Iter index.
             */
            private int current = 0;

            @Override
            public boolean hasNext() {
                return index > current;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return (E) array[current++];
                } else {
                    throw new IndexOutOfBoundsException();
                }
            }
        };
    }
}

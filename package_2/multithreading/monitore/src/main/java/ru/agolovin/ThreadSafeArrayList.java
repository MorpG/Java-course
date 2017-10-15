package ru.agolovin;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 * @param <T> Generic
 */

public class ThreadSafeArrayList<T> {
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
    public ThreadSafeArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructor. Custom array size.
     *
     * @param size int
     */
    public ThreadSafeArrayList(int size) {
        this.array = new Object[size];
    }

    /**
     * Rise array capacity.
     */
    private void riseArray() {
        this.array = Arrays.copyOf(this.array, this.index + 10);
    }

    /**
     * Iterator.
     * @return T object
     */
    public synchronized Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current != index;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return (T) array[current++];
                } else {
                    throw new IndexOutOfBoundsException();
                }
            }
        };
    }

    /**
     * Add element to array.
     *
     * @param e Object
     */
    public synchronized void add(Object e) {
        if (this.array.length > index) {
            this.array[index++] = e;
        } else {
            riseArray();
            this.array[index++] = e;
        }

    }

    /**
     * Get element from array.
     *
     * @param index int
     * @return Element Object
     */
    public synchronized T get(int index) {
        Object result;
        if (index < this.index && index >= 0) {
            result = this.array[index];
        } else {
            throw new NoSuchElementException();
        }
        return (T) result;
    }
}
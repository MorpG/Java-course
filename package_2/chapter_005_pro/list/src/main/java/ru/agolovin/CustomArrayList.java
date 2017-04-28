package ru.agolovin;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <T> generic
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class CustomArrayList<T> implements SimpleContainer {

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
    public CustomArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructor. Custom array size.
     *
     * @param size int
     */
    public CustomArrayList(int size) {
        this.array = new Object[size];
    }

    /**
     * Rise array capacity.
     */
    private void riseArray() {
        this.array = Arrays.copyOf(this.array, this.index + 10);
    }

    @Override
    public Iterator<T> iterator() {
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

    @Override
    public void add(Object e) {
        if (this.array.length > index) {
            this.array[index++] = e;
        } else {
            riseArray();
            this.array[index++] = e;
        }

    }

    @Override
    public T get(int index) {
        Object result;
        if (index < this.index && index >= 0) {
            result = this.array[index];
        } else {
            throw new NoSuchElementException();
        }
        return (T) result;
    }
}

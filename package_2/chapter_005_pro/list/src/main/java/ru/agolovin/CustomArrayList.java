package ru.agolovin;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class CustomArrayList<T> implements SimpleArrayList {

    private static final int DEFAULT_CAPACITY = 100;

    private Object[] array;

    private int index;

    public CustomArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int size) {
        this.array = new Object[size];
    }

    private void riseArray() {
        Object[] temp = Arrays.copyOf(this.array, this.index + 10);
        this.array = temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    @Override
    public void add(Object e) {
        if(this.array.length > index) {
            this.array[index++] = e;
        } else {
            riseArray();
            this.array[index++] = e;
        }

    }

    @Override
    public Object get(int index) {
        Object result;
        if (index < this.index && index >= 0) {
            result = this.array[index];
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }
}

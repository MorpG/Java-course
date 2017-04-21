package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 *
 * @param <T> generic
 */
public class SimpleArray<T> {

    /**
     * Class array.
     */
    private Object[] objects;

    /**
     * Currrent index in array.
     */
    private int index;

    /**
     * Constructor.
     *
     * @param size int
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * Add value to array.
     *
     * @param value generic
     */
    public void add(T value) {
        this.objects[this.index++] = value;
    }

    /**
     * Get value from array by index.
     *
     * @param position int
     * @return value T
     */
    public T get(int position) {
        return (T) this.objects[position];
    }

    /**
     * Delete value in array by index.
     *
     * @param position int
     */
    public void delete(int position) {
        Object[] temp = new Object[this.objects.length - 1];
        System.arraycopy(this.objects, 0, temp, 0, position);
        System.arraycopy(this.objects, position + 1, temp, position,
                this.objects.length - position - 1);
        this.objects = temp;
        this.index--;
    }

    /**
     * update value in array by index.
     *
     * @param index int
     * @param newObject generic
     */
    public void update(final int index, T newObject) {
        this.objects[index] = newObject;
    }

}

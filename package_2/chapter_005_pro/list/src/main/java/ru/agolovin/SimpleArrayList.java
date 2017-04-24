package ru.agolovin;

/**
 * @param <T> generic
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public interface SimpleArrayList<T> extends Iterable<T> {

    /**
     * Add element.
     *
     * @param t Generic
     */
    void add(T t);

    /**
     * Get element.
     *
     * @param index int
     * @return Object T
     */
    T get(int index);

}

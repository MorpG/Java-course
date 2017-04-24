package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public interface SimpleArrayList<T> extends Iterable<T> {

    void add(T e);

    T get(int index);

}

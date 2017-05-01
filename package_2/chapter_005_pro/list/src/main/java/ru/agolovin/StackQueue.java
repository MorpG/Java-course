package ru.agolovin;

/**
 * @param <E> generic
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public interface StackQueue<E> {

    /**
     * Add object.
     *
     * @param e Generic
     */
    void push(E e);

    /**
     * Get object.
     *
     * @return Object
     */
    E poll();
}

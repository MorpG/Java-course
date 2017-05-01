package ru.agolovin;

/**
 * @param <E> Generic
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class CustomStack<E> implements StackQueue<E> {

    /**
     * Inner LinkedList.
     */
    private CustomLinkedList<E> baseList = new CustomLinkedList<>();

    @Override
    public void push(E e) {
        baseList.add(e);
    }

    @Override
    public E poll() {
        return baseList.removeLast();
    }
}

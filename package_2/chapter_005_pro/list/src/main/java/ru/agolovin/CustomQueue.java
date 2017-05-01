package ru.agolovin;

/**
 * @param <E> generic
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class CustomQueue<E> implements StackQueue<E> {

    /**
     * Base Linked list.
     */
    private CustomLinkedList<E> baseList = new CustomLinkedList<>();

    @Override
    public void push(E e) {
        baseList.add(e);
    }

    @Override
    public E poll() {
        return baseList.removeFirst();
    }
}

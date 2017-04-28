package ru.agolovin;

import java.util.Iterator;

/**
 * @param <E> generic
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class CustomLinkedList<E> implements SimpleContainer<E> {

    /**
     * LinkedList size.
     */
    private int size = 0;

    /**
     * Node to first.
     */
    private Node<E> first;

    /**
     * Node to last.
     */
    private Node<E> last;


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> iterNode = first;

            private int iterIndex = 0;

            @Override
            public boolean hasNext() {
                return iterIndex < size;
            }

            @Override
            public E next() {
                Node<E> node = this.iterNode;
                E item = node.getItem();
                if (hasNext()) {
                    this.iterNode = node.getNext();
                    iterIndex++;
                } else {
                    throw new IndexOutOfBoundsException();
                }
                return item;
            }
        };
    }

    @Override
    public void add(E element) {
        final Node<E> node = this.last;
        final Node<E> newNode = new Node<>(node, element, null);
        this.last = newNode;
        this.size++;
        if (node == null) {
            first = newNode;
        } else {
            node.setNext(newNode);
        }

    }

    @Override
    public E get(int index) {
        Node<E> node = this.first;
        if (index >= 0 && index <= this.size) {
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        return node.getItem();
    }
}

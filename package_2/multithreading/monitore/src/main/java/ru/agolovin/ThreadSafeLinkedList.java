package ru.agolovin;

import java.util.Iterator;

/**
 * @param <E> Generic
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class ThreadSafeLinkedList<E> {

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

    /**
     * Iterator.
     *
     * @return <E> Object
     */
    public synchronized Iterator<E> iterator() {
        return new Iterator<E>() {

            private int iterIndex = 0;

            private Node<E> iterNode = first;

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

    /**
     * Add element to LinkedList.
     *
     * @param element E
     */
    public synchronized void add(E element) {
        final Node<E> node = this.last;
        final Node<E> newNode = new Node<>(node, element, null);
        this.size++;
        this.last = newNode;
        if (node == null) {
            first = newNode;
        } else {
            node.setNext(newNode);
        }
    }

    /**
     * Get object from LinkedList.
     *
     * @param index int
     * @return E object
     */
    public synchronized E get(int index) {
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

    /**
     * Node.
     *
     * @param <E> Generic
     */
    public static class Node<E> {

        /**
         * Current element.
         */
        private E item;

        /**
         * Next node.
         */
        private Node<E> next;
        /**
         * Previous node.
         */
        private Node<E> prev;

        /**
         * Constructor.
         *
         * @param prev    Node
         * @param element Generic
         * @param next    Node
         */
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        /**
         * Get element.
         *
         * @return element <E>
         */
        E getItem() {
            return item;
        }

        /**
         * Get nex node.
         *
         * @return E object
         */
        Node<E> getNext() {
            return next;
        }

        /**
         * Set node next.
         *
         * @param next Node.
         */
        void setNext(Node<E> next) {
            this.next = next;
        }
    }
}

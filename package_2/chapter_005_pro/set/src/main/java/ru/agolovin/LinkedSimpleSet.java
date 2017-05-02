package ru.agolovin;

import java.util.Iterator;

/**
 * @param <E> Generic.
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class LinkedSimpleSet<E> implements Iterable<E> {

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
     * Add element to set.
     *
     * @param element Generic
     */
    public void add(E element) {
        if (!contains(element)) {
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

    }

    /**
     * Check if element alredy contains in set.
     *
     * @param e Generic
     * @return boolean result
     */
    private boolean contains(E e) {
        boolean result = false;
        Node<E> start = first;
        Node<E> temp = first;
        while (temp != null) {
            if (e.equals(temp.getItem())) {
                result = true;
                break;
            }
            temp = start.getNext();
            start = temp;
        }

        return result;
    }

    /**
     * Get element from set.
     *
     * @param index int
     * @return Element
     */
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

    /**
     * Node.
     *
     * @param <E> generic
     */
    class Node<E> {
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
         * Get Node first.
         *
         * @return node.next
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

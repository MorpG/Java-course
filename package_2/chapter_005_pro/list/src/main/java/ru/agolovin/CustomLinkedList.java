package ru.agolovin;

import java.util.Iterator;
import java.util.NoSuchElementException;

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

    /**
     * Remove first element from LinkedList.
     *
     * @return Element
     */
    public E removeFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        final E element = f.getItem();
        final Node<E> next = f.getNext();
        f.setItem(null);
        f.setNext(null);
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.setPrev(null);
        }
        size--;
        return element;
    }

    /**
     * Remove last element from LinkedList.
     *
     * @return Element
     */
    public E removeLast() {
        final Node<E> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        final E element = l.getItem();
        final Node<E> prev = l.getPrev();
        l.setItem(null);
        l.setPrev(null);
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.setNext(null);
        }
        size--;
        return element;
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

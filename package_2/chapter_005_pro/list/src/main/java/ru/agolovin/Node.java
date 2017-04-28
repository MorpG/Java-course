package ru.agolovin;

/**
 * @param <E> generic
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Node<E> {

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
    public E getItem() {
        return item;
    }

    /**
     * Get Node first.
     *
     * @return node.next
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * Set node next.
     * @param next Node.
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }

    /**
     * Get node previous.
     *
     * @return node.previous
     */
    public Node<E> getPrev() {

        return prev;
    }
}

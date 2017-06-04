package ru.agolovin;

/**
 * @param <E> generic
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class BinaryTree<E extends Comparable<E>> {

    /**
     * main node.
     */
    private Node<E> mainNode;

    /**
     * Constructor.
     */
    BinaryTree(Node<E> node) {
        mainNode = node;
    }

    /**
     * add element to the tree.
     *
     * @param e Generic
     * @return boolean result
     */
    public boolean add(E e) {
        Node<E> current = mainNode;
        Node<E> parent = new Node<>(null);
        while (current != null) {
            if (e.compareTo(current.value) <= 0) {
                parent = current;
                current = current.left;
            } else {
                parent = current;
                current = current.right;
            }
        }
        if (e.compareTo(parent.value) <= 0) {
            parent.left = new Node<>(e);
        } else {
            parent.right = new Node<>(e);
        }
        return true;
    }

    /**
     * Node.
     *
     * @param <E> Generic
     */
   static class Node<E> {

        /**
         * Inner value.
         */
        E value;
        /**
         * Left.
         */
        private Node<E> left;
        /**
         * Right.
         */
        private Node<E> right;

        /**
         * Constructor.
         *
         * @param value Generic
         */
        Node(E value) {
            this.value = value;
        }
    }
}

package ru.agolovin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class BinaryTree<E extends Comparable<E>> implements Iterable<E> {

    /**
     * index add nodes.
     */
    private int nodesIndex;

    /**
     * Nodes array.
     */
    private List<Node<E>> nodes;

    /**
     * main node.
     */
    private Node<E> mainNode;

    /**
     * Constructor.
     */
    BinaryTree() {
        nodes = new ArrayList<>();
    }

    public boolean add(E e) {
        boolean result = false;
        if (this.mainNode == null) {
            this.mainNode = new Node<>(e);
        } else {
            Node<E> children = mainNode;
            while (!result) {
                if (e.compareTo(children.value) > 0) {
                    if (children.right == null) {
                        children.right = new Node<>(e);
                        result = true;
                    } else {
                        children = children.right;
                    }
                } else {
                    if (children.left == null) {
                        children.left = new Node<>(e);
                        result = true;
                    } else {
                        children = children.left;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }

    class Node<E> {

        /**
         * Left.
         */
        Node<E> left;

        /**
         * Right.
         */
        Node<E> right;

        /**
         * Inner value.
         */
        private E value;

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

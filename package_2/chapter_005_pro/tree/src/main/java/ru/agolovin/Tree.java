package ru.agolovin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @param <E> generic value
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * Nodes array.
     */
    private Node<E> node;

    /**
     * index parent.
     */
    private int index;

    @Override
    public boolean add(E parent, E child) {
        if (node == null) {
            node = new Node<>(parent);
            node.children.add(new Node<>(child));
            index++;
        } else if (node.value.equals(parent)) {
            node.children.add(new Node<>(child));
            index++;
        } else {
            addToTree(node, parent, child);
        }
        return true;
    }

    /**
     * Recursive add to tree.
     *
     * @param node   Node
     * @param parent Value
     * @param child  Value
     */
    private void addToTree(Node<E> node, E parent, E child) {
        E tempValue;
        for (int i = 0; i < node.children.size(); i++) {
            tempValue = node.children.get(i).value;
            if (tempValue.compareTo(parent) == 0) {
                node = node.children.get(i);
                node.children.add(new Node<>(child));
                index++;
                break;
            } else if (node.children.get(i).children.size() != 0) {
                addToTree(node.children.get(i), parent, child);
            }
        }
    }

    /**
     * Check for binary tree.
     *
     * @return boolean result
     */
    boolean isBinary() {
        return checkForBinary(node);
    }

    /**
     * Recursive check for binary tree.
     *
     * @param node Node.
     * @return boolean result of adding
     */
    private boolean checkForBinary(Node<E> node) {
        boolean result = true;
        for (int i = 0; i < node.children.size(); i++) {
            if (node.children.size() > 2) {
                result = false;
                break;
            } else if (node.children.get(i).children.size() != 0) {
                result = true;
                checkForBinary(node.children.get(i));
            } else {
                result = true;
            }
        }
        return result;
    }

    /**
     * Find element in a tree by value.
     *
     * @param value generic
     * @return Node<E>
     */
    Node<E> findElement(E value) {
        return findNode(node, value);
    }

    /**
     * Recursive find in a tree.
     *
     * @param node  Node
     * @param value Generic
     * @return Node<E>
     */
    private Node<E> findNode(Node<E> node, E value) {
        Node<E> res = null;
        if (node.value.equals(value)) {
            res = node;
        } else if (node.children.size() != 0) {
            for (int i = 0; i < node.children.size(); i++) {
                Node<E> temp = findNode(node.children.get(i), value);
                if (temp != null) {
                    res = temp;
                }
            }
        }
        return res;
    }

    @Override
    public Iterator<E> iterator() throws NoSuchElementException {
        return new Iterator<E>() {

            /**
             * iterIndex.
             */
            private int iterIndex = 0;
            /**
             * hasIterator.
             */
            private int next = 0;

            /**
             * iterNode.
             */
            private Node<E> iterNode = node;


            @Override
            public boolean hasNext() {
                return iterIndex <= index;
            }

            @Override
            public E next() {
                E res;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (iterIndex == 0) {
                    iterIndex++;
                    res = node.value;
                } else {
                    next = 0;
                    getValueIterator(node);
                    iterIndex++;
                    res = iterNode.value;
                }
                return res;
            }

            /**
             * @param node node.
             */
            private void getValueIterator(Node<E> node) {
                for (int i = 0; i < node.children.size(); i++) {
                    if (++next == iterIndex) {
                        iterNode = node.children.get(i);
                        break;
                    }
                    if (node.children.get(i).children.size() != 0) {
                        getValueIterator(node.children.get(i));
                    }
                }

            }

        };
    }

    /**
     * Node.
     *
     * @param <E> Generic.
     */
    static class Node<E> {

        /**
         * List children.
         */
        private List<Node<E>> children = new ArrayList<>();

        /**
         * Inner value.
         */
        private E value;

        /**
         * Get.
         * @return List
         */
        List<Node<E>> getChildren() {
            return children;
        }

        /**
         * Constructor.
         *
         * @param value generic.
         */
        Node(E value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(children, node.children)
                    && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(children, value);
        }
    }
}
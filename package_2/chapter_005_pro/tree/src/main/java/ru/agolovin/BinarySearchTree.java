package ru.agolovin;

/**
 * @param <E> generic
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

class BinarySearchTree<E extends Comparable> {

    /**
     * root of tree.
     */
    private Node<E> mainNode;

    /**
     * add value to the tree.
     *
     * @param e added e.
     * @return true if successful.
     */
    boolean add(E e) {
        Node<E> temp = this.mainNode;
        Node<E> previousNode = null;

        if (this.mainNode == null) {
            this.mainNode = new Node<>(e);
        } else {
            while (temp != null) {
                previousNode = temp;
                if (e.compareTo(temp.value) > 0) {
                    temp = temp.right;
                } else {
                    temp = temp.left;
                }
            }
            if (e.compareTo(previousNode.value) > 0) {
                previousNode.right = new Node<>(e);
            } else {
                previousNode.left = new Node<>(e);
            }
        }
        return true;
    }

    /**
     * find element in a tree.
     * @param element Generic
     * @return boolean result
     */
    boolean find(E element) {
        boolean result = false;
        Node<E> currentNode = mainNode;
        boolean flag = true;
        do {
            if (element.compareTo(currentNode.value) == 0) {
                flag = false;
                result = true;
            } else if (element.compareTo(currentNode.value) < 0) {
                if (currentNode.left == null) {
                    flag = false;
                    result = false;
                }
                currentNode = currentNode.right;
            } else if (element.compareTo(currentNode.value) > 0) {
                if (currentNode.right == null) {
                    flag = false;
                    result = false;
                }
                currentNode = currentNode.right;
            }
        } while (flag);
        return result;
    }

    /**
     * Class Node.
     *
     * @param <E> jeneric.
     */
    private class Node<E> {

        /**
         * value.
         */
        private E value;

        /**
         * left node.
         */
        private Node<E> left;

        /**
         * right node.
         */
        private Node<E> right;

        /**
         * Constructor.
         *
         * @param value node value.
         */
        Node(E value) {
            this.value = value;
        }
    }
}

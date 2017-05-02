package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class NodeCycle {

    /**
     * Check if node has cycle.
     *
     * @param first Node
     * @return true if node has cycle
     */
    boolean hasCycle(Node first) {
        boolean result = false;
        Node temp = first;
        if (temp == null || temp.getNext() == null) {
            result = false;
        } else {
            boolean flag = true;
            do {
                if (temp.getNext() != null) {
                    temp = temp.getNext();
                    if (first.equals(temp)) {
                        result = true;
                        flag = false;
                    }
                } else {
                    flag = false;
                }
            } while (flag);
        }

        return result;
    }

    /**
     * Node.
     *
     * @param <T> Generic
     */
    static class Node<T> {

        /**
         * Node value.
         */
        private T value;

        /**
         * Next node.
         */
        private Node<T> next;

        /**
         * Constructor.
         *
         * @param value Generic
         */
        Node(T value) {
            this.value = value;
        }

        /**
         * Get next node.
         *
         * @return Node
         */
        public Node<T> getNext() {
            return next;
        }

        /**
         * Set node value.
         *
         * @param next Node.
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}

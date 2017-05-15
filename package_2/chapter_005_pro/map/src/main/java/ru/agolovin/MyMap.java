package ru.agolovin;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @param <T> Generic
 * @param <V> Generic
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class MyMap<T, V> implements Iterable<V> {

    /**
     * Map capacity.
     */
    private static final int CAPACITY = 64;
    /**
     * node array.
     */
    private Node<T, V>[] nodes;

    /**
     * Array size.
     */
    private int size = 0;

    /**
     * Constructor.
     */
    MyMap() {
        this.nodes = new Node[CAPACITY];
    }

    /**
     * Insert element in the map.
     *
     * @param key   Generic
     * @param value Generic
     * @return boolean result
     */
    boolean insert(T key, V value) {
        boolean result = false;
        int hashKey = hash(key);
        int pos = getPosition(hashKey, nodes.length);
        if (nodes[pos] == null) {
            nodes[pos] = new Node<>(hashKey, key, value, null);
            size++;
            result = true;
        } else {
            Node<T, V> temp = nodes[pos];
            if (key.equals(temp.getKey())) {
                temp.setValue(value);
                result = true;
            }
        }
        return result;
    }

    /**
     * Get value from map by key.
     *
     * @param key generic
     * @return value generic
     */
    V get(T key) {
        V result = null;
        int hash = hash(key);
        int pos = getPosition(hash, nodes.length);
        if (nodes[pos] != null) {
            result = nodes[pos].getValue();
        }
        return result;
    }

    /**
     * delete element from map.
     *
     * @param key Generic
     * @return boolean result
     */
    boolean delete(T key) {
        boolean result = false;
        int hashKey = hash(key);
        Node<T, V> p;
        Node<T, V> node = null;
        p = this.nodes[getPosition(hashKey, this.nodes.length)];
        if (this.nodes.length > 0 && p != null) {
            if (p.hash == hashKey && (p.key == key || (key != null && key.equals(p.key)))) {
                node = p;
            }
        }
        if (node != null) {
            this.nodes[getPosition(hashKey, this.nodes.length)] = node.next;
            --size;
            result = true;
        }
        return result;
    }

    /**
     * Generate hash for map.
     *
     * @param key Generic
     * @return int hash
     */
    private int hash(Object key) {
        int h = key.hashCode();
        return h ^ (h >>> 16);
    }

    /**
     * Get position to insert.
     *
     * @param hashValue int
     * @param length    int
     * @return position int
     */
    private int getPosition(int hashValue, int length) {
        return (length - 1) & hashValue;
    }


    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {

            private int nextIndex = 0;

            @Override
            public boolean hasNext() {
                boolean has = false;
                int position = nextIndex;
                while (!has && position < nodes.length) {
                    if (nodes[position] != null) {
                        has = true;
                    } else {
                        position++;
                    }
                }
                return has;
            }

            @Override
            public V next() {
                Node<T, V> nextEntry = null;
                for (int i = nextIndex; i < nodes.length; i++) {
                    Node<T, V> element = nodes[i];
                    if (element != null) {
                        nextEntry = element;
                        nextIndex = i + 1;
                        break;
                    }
                }
                if (nextEntry == null) {
                    throw new NoSuchElementException();
                }
                return nextEntry.getValue();
            }
        };
    }

    /**
     * Node.
     *
     * @param <H> generic
     * @param <L> generic
     */
    static class Node<H, L> implements Map.Entry<H, L> {

        /**
         * Next node.
         */
        private Node<H, L> next;

        /**
         * temp key.
         */
        private H key;

        /**
         * temp value.
         */
        private L value;

        /**
         * int hash.
         */
        private int hash;

        /**
         * Constructor.
         *
         * @param hash  int
         * @param key   int
         * @param value int
         * @param next  node
         */
        Node(int hash, H key, L value, Node<H, L> next) {
            this.next = next;
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        /**
         * Get next node.
         *
         * @return next node
         */
        Node<H, L> getNext() {
            return this.next;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<H, L> node = (Node<H, L>) o;
            return hash == node.hash
                    && Objects.equals(key, node.key)
                    && Objects.equals(value, node.value);
        }

        @Override
        public H getKey() {
            return this.key;
        }

        @Override
        public L setValue(L value) {
            this.value = value;
            return this.value;
        }

        @Override
        public L getValue() {
            return this.value;
        }
    }
}

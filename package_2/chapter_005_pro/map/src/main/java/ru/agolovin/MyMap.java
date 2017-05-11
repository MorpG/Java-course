package ru.agolovin;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class MyMap<T, V> implements Iterable<V> {

    private Node<T, V>[] nodes;
    private int position = 0;

    public MyMap() {
        int capacity = 16;
        this.nodes = new Node[capacity];
    }

    boolean insert(T key, V value) {
        return false;
    }

    private boolean insertVal(int hash, T key, V value) {
        boolean result = false;
        int hashK = hash(key);
        int pos = getPosition(hash, nodes.length);


        if (this.nodes.length == 0 || this.nodes == null || position > (this.nodes.length - 1)) {
            this.nodes = resize();
        }
        return false;
    }

    private final Node<T, V>[] resize() {
        int newCapacity = this.nodes.length * 2;
        Node<T, V>[] newMap = (Node<T, V>[]) new Node[newCapacity];
        for (Node<T, V> node : this.nodes) {
            Node<T, V> e = node;
            if (e != null) {
                newMap[getPosition(e.hash, newCapacity)] = e;
            }
        }
        return newMap;
    }

    V get(T key) {
        return null;
    }

    boolean delete(T key) {
        return false;
    }


    private int hash(Object key) {
        int h = key.hashCode();
        return h ^ (h >>> 16);
    }

    private int getPosition(int hashValue, int length) {
        return (length - 1) & hashValue;
    }


    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public V next() {
                return null;
            }
        };
    }

    private class Node<T, V> implements Map.Entry<T, V> {

        Node<T, V> next;
        T key;
        V value;
        int hash;

        public Node(int hash, T key, V value, Node<T, V> next) {
            this.next = next;
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<T, V> node = (Node<T, V>) o;
            return hash == node.hash &&
                    Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value);
        }

        @Override
        public T getKey() {
            return this.key;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return this.value;
        }

        @Override
        public V getValue() {
            return this.value;
        }
    }
}

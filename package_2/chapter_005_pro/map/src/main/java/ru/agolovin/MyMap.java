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
    private int capacity = 16;

    public MyMap() {
        this.nodes = new Node[capacity];
    }

    public MyMap(int capacity) {
        this.capacity = capacity;
        this.nodes = new Node[this.capacity];
    }

    boolean insert(T key, V value) {
        return insertVal(hash(key), key, value);
    }

    private boolean insertVal(int hash, T key, V value) {

        boolean result = false;

        int pos = getPosition(hash, nodes.length);

        double loadFactor = 0.75;
        if (nodes.length > nodes.length * loadFactor) {
            resize();
        }
        if (nodes[pos] == null) {
            nodes[pos] = new Node<>(hash, key, value, null);
            position++;
            result = true;
        } else {
            Node<T, V> temp = nodes[pos];
            do {
                if (key.equals(temp.getKey())) {
                    temp.setValue(value);
                    break;
                }
                if (temp.getNext() == null) {
                    temp.setNext(new Node<T, V>(hash, key, value, null));
                    position++;
                    result = true;
                    break;
                }
            } while ((temp = temp.getNext()) != null);
        }
        return result;
    }

    int getSize() {
        return position;
    }

    private Node<T, V>[] resize() {
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
        V result = null;
        int hash = hash(key);
        int pos = getPosition(hash, nodes.length);
        if (nodes[pos] != null) {
            Node<T, V> e = nodes[pos];
            T k;
            k = e.key;
            do {
                if (e.hash == hash || (key != null && key.equals(k))) {
                    result = e.value;
                    break;
                }
            } while ((e = e.next) != null);
        }
        return result;
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

        Node<T, V> getNext() {
            return next;
        }

        void setNext(Node<T, V> next) {
            this.next = next;
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

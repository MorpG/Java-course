package ru.agolovin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @param <E> generic value
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * index add nodes.
     */
    private int nodesIndex;

    /**
     * Nodes array.
     */
    private List<Node<E>> nodes;

    /**
     * Constructor.
     */
    Tree() {
        nodes = new ArrayList<>();
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        int i = 0;
        while (i < nodes.size()) {
            if (nodes.get(i).children != null && nodes.get(i).value.compareTo(parent) == 0) {
                List<E> temp = nodes.get(i).children;
                temp.add(child);
                nodes.set(i, new Node<>(parent, temp));
                result = true;
                break;
            }
            i++;
        }
        if (!result) {
            List<E> temp = new ArrayList<E>();
            temp.add(child);
            nodes.add(new Node<>(parent, temp));
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() throws NoSuchElementException {
        nodesIndex = 0;
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return nodesIndex < nodes.size();
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return nodes.get(nodesIndex++).value;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * Get child for current parent.
     *
     * @param parent Generic node
     * @return list of children
     */
    public List<E> getChild(E parent) {
        List<E> result = null;
        for (Node<E> node : nodes) {
            if (parent.equals(node.value)) {
                result = node.children;
            }
        }
        return result;
    }

    /**
     * Node.
     *
     * @param <E> Generic.
     */
    class Node<E> {

        /**
         * List children.
         */
        private List<E> children;

        /**
         * Inner value.
         */
        private E value;

        /**
         * Constructor.
         *
         * @param value    Generic
         * @param children Generic
         */
        Node(E value, List<E> children) {
            this.value = value;
            this.children = children;
        }
    }
}
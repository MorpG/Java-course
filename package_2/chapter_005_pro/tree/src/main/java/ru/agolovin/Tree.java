package ru.agolovin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private int nodesIndex;

    private List<Node<E>> nodes;

    Tree() {
        nodes = new ArrayList<>();
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).children != null && nodes.get(i).value.compareTo(parent) == 0) {
                List<E> temp = nodes.get(i).children;
                temp.add(child);
                nodes.set(i, new Node<>(parent, temp));
                result = true;
                break;
            }
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
                return nodesIndex < nodes.size() && !nodes.isEmpty();
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

    public List<E> getChild(E parent) {
        List<E> buff = null;
        for (Node<E> node : nodes) {
            if (node.value.equals(parent)) {
                buff = node.children;
            }
        }
        return buff;
    }

    class Node<E> {

        List<E> children;

        E value;

        Node(E value, List<E> children) {
            this.value = value;
            this.children = children;
        }
    }
}
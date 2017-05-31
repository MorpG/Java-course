package ru.agolovin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Tree.
 *
 * @param <E> E
 *            Created by istolbov on 09.05.2017.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private int nodesIndex;

    private List<Node<E>> nodes;

    private Node<E> first;

    Tree() {
        nodes = new ArrayList<>();
    }


    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (this.first == null) {
            this.first = new Node<>(parent);
            this.first.children.add(new Node<>(child));
            result = true;
        } else if (exist(this.first, parent, child)) {
            result = true;
        }
        return result;
    }


    private boolean exist(Node<E> node, E parent, E child) {
        boolean result = false;
        if (!node.children.isEmpty()) {
            for (Node<E> x : node.children) {
                exist(x, parent, child);
            }
            result = true;
        } else if (node.value.compareTo(parent) == 0) {
            node.children.add(new Node<>(child));
            result = true;

        }
        return result;
    }


    @Override
    public Iterator<E> iterator() throws NoSuchElementException {
//        nodesIndex = 0;
//        fillRoot(this.first);
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return nodesIndex < nodes.size();
            }

            @Override
            public E next() {

                if (nodesIndex < nodes.size()) {
                    if (!nodes.get(nodesIndex).children.isEmpty()) {
                        nodes.addAll(nodes.get(nodesIndex).children);
                    }
                    return nodes.get(nodesIndex++).value;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

//    /**
//     * fillRoot.
//     *
//     * @param node node
//     */
//
//    private void fillRoot(Node<E> node) {
//        if (nodesIndex == 0 && node != null) {
//            nodes.clear();
//            nodes.add(node);
//        }
//    }

    /**
     * Node.
     *
     * @param <E> E
     */
    class Node<E> {
        /**
         * children.
         */
        List<Node<E>> children = new ArrayList<>();
        /**
         * value.
         */
        E value;

        /**
         * constructor.
         *
         * @param value value
         */
        Node(E value) {
            this.value = value;
        }
    }
}
package ru.agolovin;

import java.util.Iterator;
import java.util.List;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    @Override
    public boolean add(E parent, E child) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    class Node<E> {
        List<Node<E>> childen;
        E value;
    }
}

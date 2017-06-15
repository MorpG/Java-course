package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class BinarySearchTreeTest {

    /**
     * Test binarySearchTree class.
     */
    @Test
    public void addElementAndFindIt() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(6);
        tree.add(4);
        tree.add(8);
        tree.add(2);
        tree.add(9);
        tree.add(1);
        tree.add(11);
        assertThat(tree.find(11), is(true));
        assertThat(tree.find(5), is(false));

    }

}
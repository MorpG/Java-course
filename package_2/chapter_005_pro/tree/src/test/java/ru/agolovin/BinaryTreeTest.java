package ru.agolovin;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class BinaryTreeTest {
    /**
     * Test
     */
    @Test
    public void thenAddThenResultIs() {
        BinaryTree<Integer> tree = new BinaryTree<>(new BinaryTree.Node(16));
        tree.add(1);
        tree.add(4);
        tree.add(3);
        tree.add(6);
        tree.add(5);

        Integer[] result = new Integer[5];
        int index = 0;
//        Iterator<Integer> iter = tree.iterator();
//        while (iter.hasNext()) {
//            result[index++] = iter.next();
//        }

        Integer[] answer = {1, 3, 4, 5, 6};

        assertThat(result, is(answer));

    }

}
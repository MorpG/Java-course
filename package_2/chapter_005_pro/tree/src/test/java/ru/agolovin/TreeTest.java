package ru.agolovin;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {

    /**
     * Test for add and for iteration.
     */
    @Test
    public void whenAddTwoSameParentThenResultTwoChild() {
        Tree<String> tree = new Tree<>();

        String parentOne = "par1";
        String childOne = "childOne";
        String childTwo = "childTwo";
        String subChildOne = "subChildOne";

        tree.add(parentOne, childOne);
        tree.add(parentOne, childTwo);
        tree.add(childOne, subChildOne);

        Iterator<String> iter = tree.iterator();

        assertThat(iter.next(), is(parentOne));
        assertThat(iter.next(), is(childOne));
        assertThat(iter.next(), is(subChildOne));
        assertThat(iter.next(), is(childTwo));
    }

    /**
     * Check for binary tree.
     */
    @Test
    public void thenCheckForBinaryThenResultFalse() {
        Tree<String> tree = new Tree<>();

        String parentOne = "par1";
        String childOne = "childOne";
        String childTwo = "childTwo";
        String subChildOne = "subChildOne";

        tree.add(parentOne, childOne);
        tree.add(parentOne, childTwo);
        tree.add(parentOne, childTwo);
        tree.add(parentOne, subChildOne);
        assertThat(tree.isBinary(), is(false));
    }

    /**
     * Check for binary tree.
     */
    @Test
    public void thenCheckForBinaryThenResultTrue() {
        Tree<String> tree = new Tree<>();

        String parentOne = "par1";
        String childOne = "childOne";
        String childTwo = "childTwo";

        tree.add(parentOne, childOne);
        tree.add(parentOne, childTwo);
        assertThat(tree.isBinary(), is(true));
    }

    /**
     * Test for find element.
     */
    @Test
    public void whenFindElementThenResultIs() {
        Tree<Integer> tree = new Tree<>();
        Tree.Node<Integer> node = new Tree.Node<>(1);
        Tree.Node<Integer> node2 = new Tree.Node<>(2);
        Tree.Node<Integer> node3 = new Tree.Node<>(3);
        node.getChildren().add(node2);
        node.getChildren().add(node3);
        tree.add(1, 2);
        tree.add(1, 3);
        Tree.Node<Integer> res = tree.findElement(2);
        assertThat(res, is(node2));
    }

    /**
     * Test if element not contains in a tree.
     */
    @Test
    public void whenFindElementThenResultIsNull() {
        Tree<Integer> tree = new Tree<>();
        Tree.Node<Integer> node = new Tree.Node<>(1);
        Tree.Node<Integer> node2 = new Tree.Node<>(2);
        node.getChildren().add(node2);
        tree.add(1, 2);
        tree.add(1, 3);
        Tree.Node<Integer> res = tree.findElement(5);
        assertNull(res);
    }

    /**
     * Test for equals.
     */
    @Test
    public void whenEqualsThenResultTrue() {
        Tree.Node<Integer> node = new Tree.Node<>(1);
        Tree.Node<Integer> node2 = new Tree.Node<>(1);
        assertEquals(node, node2);
        assertEquals(node2, node);
    }

    /**
     * Test for NOT equals.
     */
    @Test
    public void whenNotEqualsThenResultTrue() {
        Tree.Node<Integer> node = new Tree.Node<>(1);
        Tree.Node<Integer> node2 = new Tree.Node<>(2);
        Tree.Node<Integer> nodeNull = new Tree.Node<>(2);
        assertNotEquals(node, node2);
        assertNotEquals(node2, node);
        assertNotEquals(nodeNull, node);
    }


}

package ru.agolovin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {

    /**
     * Test.
     */
    @Test
    public void whenAddTwoSameParentThenResultTwoChild() {
        Tree<String> tree = new Tree<>();

        String parentOne = "par1";

        tree.add(parentOne, "childOne");
        tree.add(parentOne, "childTwo");
        tree.add(parentOne, "childThree");
        tree.add(parentOne, "childFour");

        List<String> answer = new ArrayList<>();
        answer.add("childOne");
        answer.add("childTwo");
        answer.add("childThree");
        answer.add("childFour");

        assertThat(tree.getChild(parentOne), is(answer));
        assertThat(tree.isBinary(), is(false));
    }

    /**
     * Test findElement method.
     */
    @Test
    public void thenFindElementTheResultIs() {
        Tree<String> tree = new Tree<>();
        String parentOne = "par1";
        tree.add(parentOne, "childOne");
        tree.add("par2", "childOne");
        tree.add("par3", "childOne");
        Tree.Node node = tree.findElement("par1");
        Tree.Node nullResult = tree.findElement("par555");
        ArrayList<String> list = new ArrayList<>();
        list.add("childOne");
        Tree.Node answer = new Tree.Node(parentOne, list);
        assertThat(answer, is(node));
        assertNull(nullResult);

    }

    /**
     * Test node class.
     */
    @Test
    public void whenNodeNotEqualsThenResultFalse() {
        ArrayList<String> list = new ArrayList<>();
        list.add("w");
        Tree.Node<String> node1 = new Tree.Node<>("par1", list);
        Tree.Node<String> node2 = new Tree.Node<>("par2", list);
        boolean res = node1.equals(node2);
        boolean res1 = node2.equals(node1);
        assertThat(res, is(false));
        assertThat(res1, is(false));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenIteratorWork() {
        Tree<String> tree = new Tree<>();

        String parentOne = "par1";
        String parentTwo = "par2";

        tree.add(parentOne, "childOne");
        tree.add(parentOne, "childTwo");
        tree.add(parentTwo, "childTwo");
        tree.add(parentTwo, "childTwo");

        Iterator<String> iter = tree.iterator();

        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("par1"));
        assertThat(iter.next(), is("par2"));
        assertThat(iter.hasNext(), is(false));

        assertThat(tree.isBinary(), is(true));
    }
}

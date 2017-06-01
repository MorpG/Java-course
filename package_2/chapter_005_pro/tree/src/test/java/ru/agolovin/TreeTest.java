package ru.agolovin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {

    @Test
    public void whenAddTwoSameParentThenResultTwoChild() {
        Tree<String> tree = new Tree<>();

        String parentOne = "par1";

        tree.add(parentOne, "childOne");
        tree.add(parentOne, "childTwo");

        List<String> answer = new ArrayList<>();
        answer.add("childOne");
        answer.add("childTwo");

        assertThat(tree.getChild(parentOne), is(answer));
    }

    @Test
    public void whenAddToTreeThenReturnResult() {

        Tree<String> tree = new Tree<>();

        String childOne = "B";
        String childTwo = "C";
        String childThree = "D";

        tree.add("A", childOne);
        tree.add("A", childTwo);
        tree.add("A", childThree);

        tree.add(childOne, "E");
        tree.add(childOne, "F");
        tree.add(childTwo, "G");
        tree.add(childThree, "H");

        Iterator<String> iter = tree.iterator();

        StringBuilder result = new StringBuilder();
        while (iter.hasNext()) {
            result.append(iter.next());

        }
        assertThat(result.toString(), is("EFBGCHDA"));
    }
}

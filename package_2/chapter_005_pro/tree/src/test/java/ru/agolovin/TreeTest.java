package ru.agolovin;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {

    /**
     * whenWeManipulateWithTree.
     */
    @Test
    public void whenWeManipulateWithTree() {
        Tree<String> tree = new Tree<>();

        tree.add("111", "333");
        tree.add("111", "222");
        tree.add("222", "111");

        String[] target = new String[]{"111", "333"};

        ArrayList<String> result = new ArrayList<>();
        for (String s : tree) {
            result.add(s);
        }

        Assert.assertThat(result.toArray(), is(target));
    }

}
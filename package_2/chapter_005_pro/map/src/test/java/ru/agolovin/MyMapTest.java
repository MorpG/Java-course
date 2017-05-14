package ru.agolovin;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class MyMapTest {

    /**
     * test insert not equals value.
     */
    @Test
    public void whenAddSomeItemThenGetOneItem() {
        MyMap<Integer, String> map = new MyMap<>();
        boolean res1 = map.insert(1, "element1");
        map.insert(3, "element3");
        boolean res2 = map.insert(1, "element3");
        assertThat(res1, is(true));
        assertThat(res2, is(true));
        assertThat(map.get(1), is("element3"));

    }

    /**
     * Test delete value.
     */
    @Test
    public void thenAddSomeThenDeleteIt() {
        MyMap<Integer, Integer> map = new MyMap<>();
        map.insert(1, 11);
        map.insert(2, 22);
        map.insert(3, 33);
        boolean res = map.delete(2);
        boolean res1 = map.delete(5);
        assertThat(res, is(true));
        assertThat(res1, is(false));
        assertThat(map.get(3), is(33));
        assertEquals(map.get(2), null);
    }

    /**
     * Test delete from empty map.
     */
    @Test
    public void thenDeleteFromEmptyMapThenResultFalse() {
        MyMap<Integer, Integer> map = new MyMap<>();
        boolean res = map.delete(2);
        assertThat(res, is(false));
    }

    /**
     * test iterator work.
     */
    @Test
    public void thenIteratorThenResultIs() {
        MyMap<Integer, Integer> map = new MyMap<>();
        map.insert(1, 11);
        map.insert(2, 22);
        Iterator<Integer> iter = map.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(11));
        assertThat(iter.next(), is(22));
        assertThat(iter.hasNext(), is(false));
    }

    /**
     * Test iterator work.
     */
    @Test(expected = NoSuchElementException.class)
    public void thenNoElementThenNoSuchElementException() {
        MyMap<Integer, Integer> map = new MyMap<>();
        map.insert(1, 11);
        Iterator<Integer> iter = map.iterator();
        iter.next();
        iter.next();
    }

    /**
     * Test node class.
     */
    @Test
    public void thenNodeEqualsThenResultTrue() {
        MyMap.Node<Object, Object> node1 = new MyMap.Node<>(1, 2, 3, null);
        MyMap.Node<Object, Object> node2 = new MyMap.Node<>(1, 2, 3, node1);
        boolean res = node1.equals(node2);
        boolean res1 = node2.equals(node1);
        int hashNode1 = node1.hashCode();
        int resHashNode1 = 2 ^ 3;
        assertThat(hashNode1, is(resHashNode1));
        assertThat(res, is(true));
        assertThat(res1, is(true));
        assertThat(node1.getKey(), is(2));
        assertThat(node2.getValue(), is(3));
        assertThat(node2.getNext(), is(node1));
    }

    /**
     * Test node class.
     */
    @Test
    public void thenNodeNotEqualsThenResultTrue() {
        MyMap.Node<Object, Object> node1 = new MyMap.Node<>(1, 2, 3, null);
        MyMap.Node<Object, Object> node2 = new MyMap.Node<>(5, 2, 3, node1);
        boolean res = node1.equals(node2);
        boolean res1 = node2.equals(node1);
        assertThat(res, is(false));
        assertThat(res1, is(false));
    }
}

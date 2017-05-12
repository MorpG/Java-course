package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class MyMapTest {

    @Test
    public void whenAddSomeItemThenGetOneItem() {
        MyMap<Integer, String> map = new MyMap<>();
        boolean res1 = map.insert(1, "element1");
        boolean res2 = map.insert(1, "element3");
        assertThat(res1, is(true));
        assertThat(res2, is(true));
        assertThat(map.get(1), is("element3"));

    }

    @Test
    public void thenAddTwoInMapOneThneGetTwo() {
        MyMap<Integer, Integer> map = new MyMap<>(1);
        boolean res1 = map.insert(1, 11);
        boolean res2 = map.insert(2, 22);
        assertThat(res1, is(true));
        assertThat(res2, is(true));
        assertThat(map.get(1), is(11));
        assertThat(map.get(2), is(22));
    }

}
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
        map.insert(1, "element1");
        map.insert(1, "element3");
        System.out.println(map.getSize());
        assertThat(map.get(1), is("element3"));
    }

}
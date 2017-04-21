package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayTest {

    /**
     * Test add element in array.
     */
    @Test
    public void whenAdd2ThenGet2() {
        SimpleArray<Integer> ar = new SimpleArray<>(5);
        String test = "text";
        ar.add(1);
        ar.add(2);
        assertThat(ar.get(0), is(1));
        assertThat(ar.get(1), is(2));
    }

    /**
     * Test update element in array.
     */
    @Test
    public void whenUpdateFirst() {
        SimpleArray<Integer> ar = new SimpleArray<>(5);
        ar.add(1);
        ar.add(4);
        ar.update(0, 4);
        ar.update(1, 6);
        assertThat(ar.get(0), is(4));
        assertThat(ar.get(1), is(6));
    }

    /**
     * Test delete element in array.
     */
    @Test
    public void whenDelete() {
        SimpleArray<Integer> ar = new SimpleArray<>(5);
        ar.add(1);
        ar.add(2);
        ar.add(3);
        ar.delete(1);
        assertThat(ar.get(1), is(3));
    }
}
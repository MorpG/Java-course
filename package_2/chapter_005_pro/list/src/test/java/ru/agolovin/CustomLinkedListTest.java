package ru.agolovin;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class CustomLinkedListTest {

    /**
     * Test add and get methods.
     */
    @Test
    public void thenAddThreeThenGetThree() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Ts1");
        list.add("Ts2");
        list.add("Ts3");
        assertThat(list.get(2), is("Ts3"));
        assertThat(list.get(1), is("Ts2"));
        assertThat(list.get(0), is("Ts1"));
    }

    /**
     * Test throw IndexOutOfBoundsException.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void thenOutFromLinkedListThenShouldException() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Ts1");
        list.get(2);
    }

    /**
     * Test iterator methods.
     */
    @Test
    public void thenAddThreeThenIteratorResult() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("w1");
        list.add("w2");
        list.add("w3");
        Iterator<String> iter = list.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("w1"));
        assertThat(iter.next(), is("w2"));
        assertThat(iter.next(), is("w3"));
        assertThat(iter.hasNext(), is(false));

    }
}
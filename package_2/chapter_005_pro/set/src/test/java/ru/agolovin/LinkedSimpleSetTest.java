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
public class LinkedSimpleSetTest {

    /**
     * Test add and get methods.
     */
    @Test
    public void whenAddSomeThenResultIs() {
        LinkedSimpleSet<Integer> set = new LinkedSimpleSet<>();
        set.add(22);
        set.add(22);
        set.add(44);
        set.add(44);
        set.add(55);
        assertThat(set.get(0), is(22));
        assertThat(set.get(1), is(44));
        assertThat(set.get(2), is(55));
    }

    /**
     * Test iterator methods.
     */
    @Test
    public void thenAddThreeThenIteratorResult() {
        LinkedSimpleSet<String> list = new LinkedSimpleSet<>();
        list.add("w1");
        list.add("w1");
        list.add("w2");
        list.add("w2");
        Iterator<String> iter = list.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("w1"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("w2"));
        assertThat(iter.hasNext(), is(false));

    }

}
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
public class ArraySimpleSetTest {

    /**
     * Test add and get methods.
     */
    @Test
    public void whenAddSomeThenResultIs() {
        ArraySimpleSet<Integer> set = new ArraySimpleSet<>(1);
        set.add(22);
        set.add(22);
        set.add(44);
        set.add(44);
        assertThat(set.get(0), is(22));
        assertThat(set.get(1), is(44));
    }

    /**
     * Test iterator work.
     */
    @Test
    public void whenIteratorThenResultIs() {
        ArraySimpleSet<String> set = new ArraySimpleSet<>();
        set.add("q1");
        set.add("q1");
        set.add("q2");
        set.add("q2");
        Iterator<String> iter = set.iterator();

        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("q1"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("q2"));
        assertThat(iter.hasNext(), is(false));
    }


}
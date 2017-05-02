package ru.agolovin;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class CustomStackTest {

    /**
     * Test normal work.
     */
    @Test
    public void whenAddLastThenLastOut() {
        CustomStack cs = new CustomStack();
        cs.push(1);
        cs.push(2);
        cs.push(3);
        assertThat(cs.poll(), is(3));
        assertThat(cs.poll(), is(2));
        assertThat(cs.poll(), is(1));
    }

    /**
     * Test throw Exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNoElementAndPollThenThrowException() {
        CustomStack cs = new CustomStack();
        cs.poll();
    }

}
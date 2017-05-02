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
public class CustomQueueTest {
    /**
     * Test normal work.
     */
    @Test
    public void whenAddThenLastOut() {
        CustomQueue cq = new CustomQueue();
        cq.push(1);
        cq.push(2);
        cq.push(3);
        assertThat(cq.poll(), is(1));
        assertThat(cq.poll(), is(2));
        assertThat(cq.poll(), is(3));
    }

    /**
     * Test throw Exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNoElementAndPollThenThrowException() {
        CustomQueue cq = new CustomQueue();
        cq.poll();
    }

}

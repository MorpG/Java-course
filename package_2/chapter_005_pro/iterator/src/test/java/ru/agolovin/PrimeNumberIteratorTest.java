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

public class PrimeNumberIteratorTest {

    /**
     * Test work next & hasNext methods.
     */
    @Test
    public void whenGivePrimeNumberFromArrayThenResultIs() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        PrimeNumberIterator it = new PrimeNumberIterator(array);
        it.hasNext();
        assertThat(it.next(), is(2));
        it.hasNext();
        assertThat(it.next(), is(3));
        it.hasNext();
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Test exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNoPrimeNumberThenException() {
        int[] array = {1, 4, 6, 8};
        PrimeNumberIterator it = new PrimeNumberIterator(array);
        it.hasNext();
        it.next();
    }

}
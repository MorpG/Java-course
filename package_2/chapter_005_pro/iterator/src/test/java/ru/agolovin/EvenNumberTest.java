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

public class EvenNumberTest {

    /**
     * 2.
     */
    private final int two = 2;

    /**
     * 3.
     */
    private final int three = 3;

    /**
     * 4.
     */
    private final int four = 4;

    /**
     * Test array.
     */
    private int[] array = {1, two, three, four};

    /**
     * Test class.
     */
    private EvenNumber en = new EvenNumber(array);

    /**
     * Test get even number from array.
     */
    @Test
    public void whenNextReturnEvenNumberThenResultIs() {
        assertThat(en.next(), is(two));
        assertThat(en.next(), is(four));
    }

    /**
     * Test work hasNext() method.
     */
    @Test
    public void whenTestHasNextThenResultIs() {
        assertThat(en.hasNext(), is(true));
        en.next();
        en.next();
        assertThat(en.hasNext(), is(false));
    }

    /**
     * Test out from array.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenTestExceptionThenResultIs() {
        for (int i = 0; i < three; i++) {
            en.next();
        }
    }
}

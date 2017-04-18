package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayIteratorTest {

    /**
     * Start array value.
     */
    private int index = 1;

    /**
     * Test array.
     */
    private int[][] array = {{index++, index++}, {index++, index++}};

    /**
     * Test class.
     */
    private ArrayIterator it = new ArrayIterator(array);

    /**
     * testing data.
     */
    @Test
    public void whenArrayIteratorReturnArrayThrnResultIs() {
        for (int[] anArray : array) {
            for (int anAnArray : anArray) {
                int result = it.next();
                assertThat(result, is(anAnArray));
            }
        }

    }

    /**
     * Testing method has next.
     */
    @Test
    public void whenTestOutFromNexThenResultIs() {
        boolean result;
        for (int[] anArray : array) {
            for (int anAnArray : anArray) {
                result = it.hasNext();
                assertThat(result, is(true));
                it.next();
            }
        }
        boolean result2 = it.hasNext();
        assertThat(result2, is(false));
    }

    /**
     * test out from array.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenOutFromArrayThenException() {
        for (int[] anArray : array) {
            for (int anAnArray : anArray) {
                it.next();
            }
        }
        it.next();
    }

}
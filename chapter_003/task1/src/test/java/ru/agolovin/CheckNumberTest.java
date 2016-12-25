package ru.agolovin;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Check number in the input stream class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class CheckNumberTest {

    /**
     * Test then input even number, then method return true.
     */
    @Test
    public final void whenInputEvenNumberThenReturnTrue() {
        CheckNumber check = new CheckNumber();
        InputStream in = new ByteArrayInputStream("6".getBytes());
        boolean result = check.isNumber(in);
        assertThat(result, is(true));
    }

    /**
     * Test then input not even number, then method return false.
     */
    @Test
    public final void whenInputNotEvenNumberThenReturnTrue() {
        CheckNumber check = new CheckNumber();
        InputStream in = new ByteArrayInputStream("5".getBytes());
        boolean result = check.isNumber(in);
        assertThat(result, is(false));
    }

    /**
     * Test then input not Number, then method return false.
     */
    @Test
    public final void whenInputNotNumberThenResultFalse() {
        CheckNumber check = new CheckNumber();
        InputStream in = new ByteArrayInputStream("e".getBytes());
        boolean result = check.isNumber(in);
        assertThat(result, is(false));
    }
}

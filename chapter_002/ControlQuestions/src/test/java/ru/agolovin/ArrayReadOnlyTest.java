package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test methods for ArrayReadOnly class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayReadOnlyTest {
    /**
     * Test for readOnly class.
     */
    @Test
    public final void whenArrayReadThen() {
        final int first = 1;
        final int second = 5;
        final int third = 8;
        ArrayReadOnly ar = new ArrayReadOnly(
                new int[]{first, second, third}
                );

        final int test = 4;
        ar.getReadOnlyArray()[0] = test;
        assertThat(ar.getReadOnlyArray()[0], is(1));
    }
}

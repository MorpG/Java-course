package ru.agolovin;

import org.junit.Test;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class FileSortExceptionTest {
    /**
     * Test FileSortException.
     */
    @Test(expected = RuntimeException.class)
    public final void whenSomethingThen() {
        throw new FileSortException("test");
    }

}
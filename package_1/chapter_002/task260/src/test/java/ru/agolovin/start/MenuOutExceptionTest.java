package ru.agolovin.start;

import org.junit.Test;

/**
 * unHandle exception for menu class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class MenuOutExceptionTest {
    /**
     * Test MenuOutException.
     */
    @Test (expected = RuntimeException.class)
    public final void whenSomethingThen() {
        throw new MenuOutException("test");
    }
}
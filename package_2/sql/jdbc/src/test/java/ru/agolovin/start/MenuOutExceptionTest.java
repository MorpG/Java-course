package ru.agolovin.start;

import org.junit.Test;
import ru.agolovin.start.MenuOutException;

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
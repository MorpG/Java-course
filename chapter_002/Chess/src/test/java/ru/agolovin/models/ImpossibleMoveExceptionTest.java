package ru.agolovin.models;

import org.junit.Test;

/**
 * unHandle exception for menu class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ImpossibleMoveExceptionTest {
    /**
     * Test ImpossibleMoveException.
     */
    @Test (expected = RuntimeException.class)
    public final void whenSomethingThen() {
        throw new ImpossibleMoveException("test");
    }
}

package ru.agolovin.models;

/**
 * unHandle exception for main class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ImpossibleMoveException extends RuntimeException {
    /**
     * Return message to parent.
     * @param msg String
     */
    public ImpossibleMoveException(final String msg) {
        super(msg);
    }
}

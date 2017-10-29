package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class OplimisticException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param msg String
     */
    public OplimisticException(String msg) {
        super(msg);
    }
}

package ru.agolovin.start;

/**
 * unHandle exception for menu class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class MenuOutException extends RuntimeException {
    /**
     * Return message to parent.
     *
     * @param msg String
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}

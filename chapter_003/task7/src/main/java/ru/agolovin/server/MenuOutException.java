package ru.agolovin.server;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class MenuOutException extends RuntimeException {

    /**
     * Runtime Exception.
     * @param msg String
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}

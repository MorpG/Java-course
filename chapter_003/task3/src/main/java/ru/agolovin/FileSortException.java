package ru.agolovin;

/**
 * unHandle exception for main class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class FileSortException extends RuntimeException {

    /**
     * Return message to parent.
     *
     * @param msg String
     */
    FileSortException(String msg) {
        super(msg);
    }
}

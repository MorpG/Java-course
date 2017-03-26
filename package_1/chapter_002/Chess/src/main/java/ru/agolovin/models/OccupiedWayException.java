package ru.agolovin.models;

/**
 * unHandle exception for menu class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
class OccupiedWayException extends RuntimeException {
    /**
     * Return message to parent.
     * @param msg String
     */
    OccupiedWayException(final String msg) {
        super(msg);
    }
}

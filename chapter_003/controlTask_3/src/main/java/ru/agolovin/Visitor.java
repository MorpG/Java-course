package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Visitor {

    /**
     * Visitor entry time.
     */
    private long in;

    /**
     * Visitor out time.
     */
    private long out;

    /**
     * Constructor.
     *
     * @param in  long
     * @param out long
     */
    public Visitor(long in, long out) {
        this.in = in;
        this.out = out;
    }

    /**
     * Get entry time.
     *
     * @return entry time long
     */
    public long getIn() {
        return in;
    }

    /**
     * get out time.
     *
     * @return out time long
     */
    public long getOut() {
        return out;
    }
}

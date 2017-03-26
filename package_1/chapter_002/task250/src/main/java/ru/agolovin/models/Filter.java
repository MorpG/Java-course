package ru.agolovin.models;

/**
 * Filter methods.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Filter {

    /**
     * variable class filter.
     *
     * @param filter
     */
    private String filter;

    /**
     * filter set.
     *
     * @param outFilter outFilter
     *
     */
    public Filter(final String outFilter) {
        this.filter = outFilter;
    }

    /**
     * @return filter
     */
    public final String getFilter() {
        return this.filter;
    }

}

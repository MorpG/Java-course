package ru.agolovin;

import java.util.Iterator;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public interface ConvertIterator {

    /**
     * Iterator iterators.
     *
     * @param it Iterator
     * @return Integer value
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it);
}

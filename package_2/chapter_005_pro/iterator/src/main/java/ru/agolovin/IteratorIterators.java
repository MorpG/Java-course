package ru.agolovin;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class IteratorIterators implements ConvertIterator, Iterator<Integer> {

    /**
     * Private iterator.
     */
    private Iterator<Iterator<Integer>> source;

    /**
     * Inner iterator.
     */
    private Iterator<Integer> current;

    @Override
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.source = it;
        if (this.source.hasNext()) {
            this.current = this.source.next();
        }
        return this;
    }

    @Override
    public boolean hasNext() {
        return this.current.hasNext() || this.source.hasNext();
    }

    @Override
    public Integer next() {
        Integer result;
        if (current.hasNext()) {
            result = current.next();
        } else if (source.hasNext()) {
            current = source.next();
            result = current.next();
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }
}

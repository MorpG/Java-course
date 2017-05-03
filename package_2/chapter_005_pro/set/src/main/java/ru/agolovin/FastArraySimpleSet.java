package ru.agolovin;

import java.util.Arrays;

/**
 * @param <E> Generic
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class FastArraySimpleSet<E> extends ArraySimpleSet<E> {

    @Override
    public void add(E e) {
        if (contains(e) < 0) {
            int ind = getIndex();
            if (getArray().length >= ind) {
                riseArray();
            }
            setValue(ind, e);
            Arrays.sort(getArray());
            setIndex(++ind);
        }
    }

    /**
     * Rise array.
     */
    void riseArray() {
        setArray(Arrays.copyOf(getArray(), (getIndex() + 1)));
    }

    /**
     * Check if element already contains in array.
     *
     * @param e Generic
     * @return index element in array. Return -1 if not contains
     */
    private int contains(E e) {
        int low = 0;
        int high = getIndex() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            @SuppressWarnings("rawtypes")
            Comparable midVal = (Comparable) getArray()[mid];
            @SuppressWarnings("unchecked")
            int cmp = midVal.compareTo(e);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -(low + 1);  // key not found.
    }
}

package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class FastArraySimpleSet<E> extends ArraySimpleSet<E> {

    @Override
    public void add(E e) {
        if (contains(e) == -1) {
            if (array.length > index) {
                riseArray();
            }
            if (index == 0) {
                array[index] = e;
            } else {
                add(getAddIndex(e), e);
            }
            index++;

        }
    }

    private void add(int addIndex, E element) {
        E[] temp = (E[]) new Object[array.length + 1];
        System.arraycopy(array, 0, temp, 0, addIndex);
        temp[addIndex] = element;
        System.arraycopy(array, addIndex, temp, addIndex + 1, array.length - addIndex);
        this.array = temp;
    }

    private int getAddIndex(E e) {
        int hashCode = e.hashCode();
        int leftBorder = -1;
        int rightBorder = index;
        int middle;
        while (leftBorder < rightBorder - 1) {
            middle = (leftBorder + rightBorder)/ 2;
            if (array[middle].hashCode() < hashCode) {
                leftBorder = middle;
            } else {
                rightBorder = middle;
            }
        }
        return rightBorder;
    }

    private int contains(E e) {
        int i = -1;
        if (this.array != null) {
            int low = 0, high = this.array.length, mid;
            while (low < high) {
                mid = (low + high)/2; // Можно заменить на расчёт в беззнаковом типе: (low + high) >>> 1
                if (e.equals(this.array[mid])) {
                    i = mid;
                    break;
                } else {
//                    if (e < this.array[mid]) {
                        high = mid;
//                    } else {
                        low = mid + 1;
                    }
                }
            }
//        }
        return i;
    }
}

package ru.agolovin;

import java.util.Arrays;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

class SortArrayCollectionPro {


    /**
     * main array.
     */
    private Integer[] mainArray;

    /**
     * Create one sorted array from two sorted.
     *
     * @param arrOne Array
     * @param arrTwo Array
     * @return array
     */
    Integer[] createNewArrayFromEntry(Integer[] arrOne, Integer[] arrTwo) {
        mainArray = new Integer[arrOne.length + arrTwo.length];
        Arrays.sort(arrTwo);
        int mainLength = mainArray.length;
        int i = 0;
        int j = 0;
        int n;
        for (n = 0; n < mainLength; n++) {
            if (arrOne[i] < arrTwo[j]) {
                mainArray[n] = arrOne[i];
                if (i < arrOne.length - 1) {
                    i++;
                } else {
                    System.arraycopy(arrTwo, j, mainArray, n, arrTwo.length - 1);
                    break;
                }
            } else {
                mainArray[n] = arrTwo[j];
                if (j < arrTwo.length - 1) {
                    j++;
                } else {
                    System.arraycopy(arrOne, i, mainArray, n + 1, arrOne.length - 1);
                    break;
                }
            }

        }
        return this.mainArray;
    }
}

package ru.agolovin;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class SortArrayCollectionPro<T> {

    private T[] firstArray;

    private T[] secondArray;

//    SortArrayCollectionPro(T[] firstArrayIn, T[] secondArrayIn) {
//        this.firstArray = firstArrayIn;
//        this.secondArray = secondArrayIn;
//    }

    void sortArray(T[] arr) {
        Arrays.sort(arr);
    }

    T[] createNewArrayFromEntry(T[] arrOne, T[] arrTwo) {
        Class<T> t = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getClass();
        T[] arr = null;
        try {
            arr = (T[]) t.newInstance();
            System.arraycopy(arrOne, 0, arr, 0, arrOne.length - 1);
            System.arraycopy(arrTwo, 0, arr, arrOne.length, arrTwo.length - 1);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return arr;
    }

}

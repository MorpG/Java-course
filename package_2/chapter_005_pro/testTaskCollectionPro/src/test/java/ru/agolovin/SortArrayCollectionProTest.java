package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class SortArrayCollectionProTest {

    @Test
    public void whenThen() {
        SortArrayCollectionPro<Integer> sort = new SortArrayCollectionPro<>();
        Integer[] arr = {3, 4, 1, 5};
        Integer[] res = {1, 3, 4, 5};
        sort.sortArray(arr);
        assertThat(arr, is(res));

    }

    @Test
    public void wT() {
        SortArrayCollectionPro<Integer> sort = new SortArrayCollectionPro<>();
        Integer[] arrOne = {1, 2, 3};
        Integer[] arrTwo = {4, 5, 6};
        Integer[] res = {1, 2, 3, 4, 5, 6};

        Integer[] answer = sort.createNewArrayFromEntry(arrOne, arrTwo);



    }




}
package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class SortArrayCollectionProTest {

    /**
     * Test methods works.
     */
    @Test
    public void whenFirstEndSecondThenResultIs() {
        SortArrayCollectionPro sort = new SortArrayCollectionPro();
        Integer[] arrOne = {1, 5, 6};
        Integer[] arrTwo = {2, 3, 4};
        Integer[] res = {1, 2, 3, 4, 5, 6};
        Integer[] answer = sort.createNewArrayFromEntry(arrOne, arrTwo);
        assertThat(answer, is(res));


    }


}
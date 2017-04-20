package ru.agolovin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class IteratorIteratorsTest {

    /**
     * List for iterator0.
     */
    private List<Integer> list0;

    /**
     * List for iterator1.
     */
    private List<Integer> list1;

    /**
     * List for iterator2.
     */
    private List<Integer> list2;

    /**
     * List for Iterators.
     */
    private List<Iterator<Integer>> listIterators;

    /**
     * Test class.
     */
    private IteratorIterators it = new IteratorIterators();


    /**
     * Test method convert.
     */
    @Test
    public void whenGetAllFromIteratorThenResultContainsAll() {

        prepare();

        Iterator<Iterator<Integer>> globalIterator = listIterators.iterator();

        Iterator<Integer> resultIter = it.convert(globalIterator);

        ArrayList<Integer> result = new ArrayList<>();

        while (resultIter.hasNext()) {
            result.add(resultIter.next());
        }

        ArrayList<Integer> answer = new ArrayList<>(
                Arrays.asList(4, 2, 0, 4, 6, 4, 9, 0, 9, 8, 7, 5, 1, 3, 5, 6, 7, 0, 9, 8, 4));

        assertThat(result, is(answer));

    }


    /**
     * Test out from all iterators.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenExitFromIteratorThenThrowException() {
        prepare();
        Iterator<Iterator<Integer>> globalIterator = listIterators.iterator();
        Iterator<Integer> resultIter = it.convert(globalIterator);
        int length = list0.size() + list1.size() + list2.size();
        for (int i = 0; i < length; i++) {
            resultIter.next();
        }
        resultIter.next();

    }

    /**
     * SetUp iterators.
     */
    private void prepare() {
        this.list0 = new ArrayList<>(Arrays.asList(4, 2, 0, 4, 6, 4, 9));
        this.list1 = new ArrayList<>(Arrays.asList(0, 9, 8, 7, 5));
        this.list2 = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 7, 0, 9, 8, 4));

        Iterator<Integer> iterator0 = list0.iterator();
        Iterator<Integer> iterator1 = list1.iterator();
        Iterator<Integer> iterator2 = list2.iterator();

        this.listIterators = new ArrayList<>(Arrays.asList(iterator0, iterator1, iterator2));
    }


}
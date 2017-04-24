package ru.agolovin;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class CustomArrayListTest {

    /**
     * Test iterator for CustomArrayList.
     */
    @Test
    public void whenTestIterator() {
        CustomArrayList<Integer> customArrayList = new CustomArrayList<>(2);
        customArrayList.add(1);
        customArrayList.add(2);
        Integer[] answer = new Integer[2];
        answer[0] = customArrayList.get(0);
        answer[1] = customArrayList.get(1);

        Integer[] res = new Integer[2];
        int pos = 0;
        Iterator<Integer> element = customArrayList.iterator();

        while (element.hasNext()) {
            res[pos++] = element.next();
        }

        assertThat(res, is(answer));
    }

    /**
     * Test add and get method from CustomArrayList.
     */
    @Test
    public void whenAddTwoThenGetTwoByIndex() {
        CustomArrayList<Integer> customArrayList = new CustomArrayList<>();
        customArrayList.add(1);
        customArrayList.add(2);
        assertThat(customArrayList.get(0), is(1));
        assertThat(customArrayList.get(1), is(2));
    }

    /**
     * Test dynamically rise.
     */
    @Test
    public void whenRiseArraySizeThenResultIs() {
        CustomArrayList<Integer> customArrayList = new CustomArrayList<>(1);
        customArrayList.add(1);
        customArrayList.add(2);
        assertThat(customArrayList.get(0), is(1));
        assertThat(customArrayList.get(1), is(2));
    }

}
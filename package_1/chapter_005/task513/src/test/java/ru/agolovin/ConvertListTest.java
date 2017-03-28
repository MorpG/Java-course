package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


import java.util.ArrayList;
import java.util.List;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class ConvertListTest {

    @Test
    public void whenConvertArrayToListThenNewListIs() throws Exception {
        final int arrayLength = 7;
        List<Integer> list;
        int[][] array = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        ConvertList convertList = new ConvertList();
        list = convertList.toList(array);
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < arrayLength; i++) {
            result.add(i);
        }
        assertThat(result, is(list));
    }



    @Test
    public void toArray() throws Exception {
    }

}
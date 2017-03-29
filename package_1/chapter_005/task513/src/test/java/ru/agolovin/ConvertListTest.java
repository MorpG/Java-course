package ru.agolovin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class ConvertListTest {

    /**
     * Test class.
     */
    private ConvertList convertList = new ConvertList();

    /**
     * List.
     */
    private List<Integer> list = new ArrayList<>();

    /**
     * result list.
     */
    private List<Integer> result = new ArrayList<>();


    /**
     * test convert array[][] to list.
     *
     * @throws Exception Exception
     */
    @Test
    public void whenConvertArrayToListThenNewListIs() throws Exception {
        final int arrayLength = 7;
        int ind = 1;
        int[][] array = new int[][]{{ind++, ind++}, {ind++, ind++}, {ind++, ind++}};
        this.list = this.convertList.toList(array);
        for (int i = 1; i < arrayLength; i++) {
            this.result.add(i);
        }
        assertThat(this.result, is(this.list));
    }

    /**
     * test convert list to array[][] and fill empty with zero.
     *
     * @throws Exception Exception
     */
    @Test
    public void whenConvertListToArrayAndFillZeroThenNewArrayIs() throws Exception {
        final int arrayLength = 7;
        final int rows = 3;
        for (int i = 1; i <= arrayLength; i++) {
            this.list.add(i);
        }
        int ind = 1;
        int[][] result = this.convertList.toArray(list, rows);
        int[][] array = new int[][]{{ind++, ind++, ind++}, {ind++, ind++, ind++}, {ind++, 0, 0}};

        assertThat(result, is(array));
    }

    /**
     * test convert list to array[][].
     *
     * @throws Exception Exception
     */
    @Test
    public void whenConvertListToArrayAndNotFillZeroThenNewArrayIs() throws Exception {
        final int arrayLength = 6;
        final int rows = 3;
        for (int i = 1; i <= arrayLength; i++) {
            list.add(i);
        }
        int ind = 1;
        int[][] result = this.convertList.toArray(list, rows);
        int[][] array = new int[][]{{ind++, ind++, ind++}, {ind++, ind++, ind++}};

        assertThat(result, is(array));
    }

    /**
     * Test convert List<int[]> to List<Integer>.
     */
    @Test
    public void whenConvertToArraysWhenItCollectInOne() {
        final int arrayLength = 6;
        int ind = 1;
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{ind++, ind++});
        list.add(new int[]{ind++, ind++, ind++, ind++});

        List<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= arrayLength; i++) {
            answer.add(i);
        }
        List<Integer> result = convertList.convert(list);
        assertThat(result, is(answer));

    }

}
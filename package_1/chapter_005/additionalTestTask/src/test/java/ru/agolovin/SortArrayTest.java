package ru.agolovin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class SortArrayTest {

    /**
     * Incoming words.
     */
    private String[] words = {
            "K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"
    };

    /**
     * Answer word for UpSort.
     */
    private String[] answerUp = {
            "K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1",
            "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"
    };

    /**
     * Answer words for DescSort.
     */
    private String[] answerDesc = {
            "K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2",
            "K1\\SK1\\SSK1"
    };

    /**
     * Test UpSort.
     */
    @Test
    public void whenSortUpThenResultIs() {
        SortArray sortArray = new SortArray();
        List<String> result = sortArray.sorted(words);
        List<String> answer = new ArrayList<>();
        Collections.addAll(answer, answerUp);
        assertThat(result, is(answer));
    }

    /**
     * Test DescSort.
     */
    @Test
    public void whenSortDescThenResultIs() {
        SortArray sortArray = new SortArray();
        List<String> result = sortArray.sortDesc(words);
        List<String> answer = new ArrayList<>();
        Collections.addAll(answer, answerDesc);
        assertThat(result, is(answer));
    }
}

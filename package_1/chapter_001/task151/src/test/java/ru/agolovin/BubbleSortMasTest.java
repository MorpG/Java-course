package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BubbleSortMasTest {
    @Test
    public void whenMassExistThenResult() {
        int [] result = {5, 1 , 3 , 20};
        BubbleSortMas bs = new BubbleSortMas();
        bs.bubbleSort(result);
        int[] answ = {1, 3, 5 , 20};
        assertThat(result, is(answ));
    }

}
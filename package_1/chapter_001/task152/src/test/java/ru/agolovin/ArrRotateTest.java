package ru.agolovin;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArrRotateTest {

    @Test
    public void whenWorkArrRotateThanResultIs() {
        int[][] result = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ArrRotate ar = new ArrRotate();
        ar.arrRotate(result);
        int[][] answ = {{3, 6, 9}, {2, 5, 8}, {1, 4, 7}};
        assertThat(result, is(answ));
    }
}

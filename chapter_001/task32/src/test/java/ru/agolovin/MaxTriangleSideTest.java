package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxTriangleSideTest {

    @Test
    public void whenMaxTriangleSideExistTheResult() {
        double result = MaxTriangleSide.maxSide(5.0, 4.0, 10.0);
        assertThat(result, is(10.0));
    }
}
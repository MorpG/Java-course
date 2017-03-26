package ru.agolovin;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PointTest {
    @Test
    public void whenPointNotEqualsItselfThenResultIs() {
        Point point = new Point(5, 6);
        Point point1 = new Point(9, 6);
        double result = point.distanceTo(point1);
        assertThat(result, is(4.0));
    }

    @Test
    public void whenPointEqualsItselfThenResultIs() {
        Point point = new Point(4, 6);
        Point point1 = new Point(4, 6);
        double result = point.distanceTo(point1);
        assertThat(result, is(0.0));
    }
}
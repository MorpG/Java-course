package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxTriangleSideTest {

    @Test
    public void whenMaxTriangleSideExistThenResult() {
        Point point1 = new Point(3, 5);
        Point point2 = new Point(6, 1);
        Point point3 = new Point(3, 1);
        Triangle tr = new Triangle(point1, point2, point3);
        tr.sideLength();
        double result = MaxTriangleSide.maxSide(tr.ab, tr.ac, tr.bc);
        assertThat(result, is(5.0));
    }
}
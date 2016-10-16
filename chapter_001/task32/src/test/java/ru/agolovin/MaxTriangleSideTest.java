package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.*;

public class MaxTriangleSideTest {

    @Test
    public void whenMaxTriangleSideNotExistTheResult() {
        Point point1 = new Point(9, 4);
        Point point2 = new Point(4, 9);
        Point point3 = new Point(6, 7);
        Triangle triangle = new Triangle(point1, point2, point3);
        MaxTriangleSide triangleSide = new MaxTriangleSide();
        double result = triangleSide.maxSide(triangle);
        assertThat(result, is(0.0));
    }

    @Test
    public void whenMaxTriangleSideExistThenResultIs() {
        Point point1 = new Point(1, 4);
        Point point2 = new Point(4, 9);
        Point point3 = new Point(6, 10);
        Triangle triangle = new Triangle(point1, point2, point3);
        MaxTriangleSide triangleSide = new MaxTriangleSide();
        double result = triangleSide.maxSide(triangle);
        assertThat(result, is(closeTo(7.81, 0.001)));
    }
}
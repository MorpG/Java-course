package ru.agolovin;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.*;

public class TriangleTest {

    @Test
    public void whenTriangleExistThenResultIs() {
        Point point1 = new Point(1, 4);
        Point point2 = new Point(7, 9);
        Point point3 = new Point(6, 10);
        Triangle triangle = new Triangle(point1, point2, point3);
        double area = triangle.area();
        assertThat(area, is(closeTo(5.500, 0.0005)));
    }

    @Test
    public void whenTriangleNotExistThenResultIs() {
        Point point1 = new Point(9, 4);
        Point point2 = new Point(4, 9);
        Point point3 = new Point(6, 7);
        Triangle triangle = new Triangle(point1, point2, point3);
        double area = triangle.area();
        assertThat(area, is(0.0));
    }
}

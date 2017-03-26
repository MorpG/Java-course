package ru.agolovin;

public class Triangle {
    public Point a;
    public Point b;
    public Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        double area;
        double ab = a.distanceTo(b);
        double ac = a.distanceTo(c);
        double bc = b.distanceTo(c);
        if ((ab + ac > bc) && (ac + bc > ab) && (bc + ab > ac)) {
            double per = (ab + ac + bc) / 2;
            area = Math.sqrt(per * (per - ab) * (per - ac) * (per - bc));
        } else {
            area = 0.0;
        }
        return area;
    }
}
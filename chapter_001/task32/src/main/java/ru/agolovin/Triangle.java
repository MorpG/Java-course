package ru.agolovin;

public class Triangle {
    public Point a;
    public Point b;
    public Point c;

    public double ab;
    public double ac;
    public double bc;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        double area;
        this.ab = a.distanceTo(b);
        this.ac = a.distanceTo(c);
        this.bc = b.distanceTo(c);
        if ((ab + ac > bc) && (ac + bc > ab) && (bc + ab > ac)) {
            double per = (ab + ac + bc) / 2;
            area = Math.sqrt(per * (per - ab) * (per - ac) * (per - bc));
        } else {
            area = 0.0;
        }
        return area;
    }
}
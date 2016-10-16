package ru.agolovin;


public class MaxTriangleSide {
    public Triangle t;

    public double maxSide(Triangle a) {
        double max;
        this.t = a;

        if (t.area() != 0.0)
            if ((t.ab >= t.ac) && (t.ab >= t.bc))
                max = t.ab;
            else if ((t.ac >= t.bc) && (t.ac >= t.ab))
                max = t.ac;
            else
                max = t.bc;
        else
            max = 0;

        return max;
    }
}

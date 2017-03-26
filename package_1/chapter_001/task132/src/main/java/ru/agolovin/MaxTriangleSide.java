package ru.agolovin;

public class MaxTriangleSide {

    public static double maxSide(double ... args) {
        double max = 0.0;
        for (double x : args)
            if (x > max) max = x;
        return max;
    }
}

package ru.agolovin;

public class Square {
    public float a;
    public float b;
    public float c;

    public void setParameters(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public float calculate(int x) {
        float result =a * x * x + b * x + c;
        return result;
    }

    public void show(int st, int fin, int stp) {
        for (int i = st; i <= fin; i += stp) {
            System.out.println(calculate(i));
        }
    }
}
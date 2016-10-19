package ru.agolovin;


public class BubbleSortMas {


    public void bubbleSort(int[] t) {

        for (int i = t.length - 1; i >= 0; i--)
            for (int j = 0; j < i; j++)
                if (t[j] > t[j + 1]) {
                    int tmp = t[j];
                    t[j] = t[j + 1];
                    t[j + 1] = tmp;
                }

    }
}
package ru.agolovin;


public class ArrRotate {
    public void arrRotate(int[][] arr) {
        int n = arr.length;
        int[][] temp = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                temp[i][j] = arr[j][n - i - 1];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = temp[i][j];
    }
}
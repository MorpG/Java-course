package ru.agolovin;

public class RemoveDuplicates {

    public String[] removeDuplicates(String[] arr) {
        int length = arr.length;
        int newLength = 0;
        String [] tmp = new String[arr.length];
        for (int i = 0; i < length; i++) {
            boolean flag = true;
            for (int j = i; j < length; j++) {
                if ((arr[i].equals(arr[j])) && (i != j)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                tmp[newLength] = arr[i];
                newLength++;
            }
        }
        if (newLength == 0)
            return arr;
        else {
            String[] nArr = new String[newLength];
            for (int i = 0; i < newLength; i++) {
                nArr[i] = tmp[i];
            }
            return nArr;
        }
    }
}
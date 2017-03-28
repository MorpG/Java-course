package ru.agolovin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class ConvertList {

    public List<Integer> toList(int[][] array) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int[] element : array) {
            for (int sub : element) {
                arrayList.add(sub);
            }

        }
        return arrayList;
    }

    public int[][] toArray(List<Integer> list, int rows) {
        return new int[0][];
    }
}

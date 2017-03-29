package ru.agolovin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Вам необходимо создать класс ConvertList.
 * В нём написать 2 метода:
 * public List<Integer> toList (int[][] array) {} - в метод прходит двумерный массив целых чисел,
 * необходимо пройтись по всем элементам массива и добавить их в List<Integer>
 * public int[][] toArray (List<Integer> list, int rows) {} - метод toArray должен равномерно разбить лист
 * на количество строк двумерного массива. В методе toArray должна быть проверка - если количество элементов
 * не кратно количеству строк - оставшиеся значения в массиве заполнять нулями
 * Например в результате конвертации листа со значениями (1,2,3,4,5,6,7) с разбиением на 3 строки должен
 * получиться двумерный массив {{1, 2, 3} {4, 5, 6} {7, 0 ,0}}
 *
 * В классе ConvertList написать метод:
 * public List<Integer> convert (List<int[]> list)
 *  В этом методе вы должны пройтись по всем элементам всех массивов в списке list и добавить их в один
 *  общий лист Integer
 *  Массивы в списке list могут быть разного размера
 *
 * Например:
 * list.add(new int[]{1, 2})
 * list.add(new int[]{3, 4, 5, 6})
 * List<Integer> result = convertList.convert(list)
 * List<Integer> result будет содержать элементы: (1, 2, 3, 4, 5, 6)
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

class ConvertList {

    /**
     * Convert int[][] array to List<Integer>.
     *
     * @param array int[][]
     * @return list List<Integer>
     */
    public List<Integer> toList(int[][] array) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int[] element : array) {
            for (int sub : element) {
                arrayList.add(sub);
            }

        }
        return arrayList;
    }

    /**
     * Convert List<Integer> to int[][] array With the number of lines. Free elements are replaced by 0.
     *
     * @param list List<Integer>
     * @param rows int
     * @return array int[][]
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] array;
        int listSize = list.size();

        int lines = listSize % rows == 0 ? listSize / rows : listSize / rows + 1;
        array = new int[lines][rows];

        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < rows; j++) {
                if (iterator.hasNext()) {
                    array[i][j] = iterator.next();
                } else  {
                    array[i][j] = 0;
                }

            }
        }
        return array;
    }

    /**
     * Convert List<int[]> to List<Integer>.
     * @param list List<int[]>
     * @return List<Integer>
     */
    public List<Integer> convert(List<int[]> list) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int[] element : list) {
            for (int sub : element) {
                arrayList.add(sub);
            }
        }
        return arrayList;
    }
}

package ru.agolovin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class SortArray {

    /**
     * Сортировка списка сотрдуников по возрастанию.
     *
     * @param strings String[]
     * @return list ArrayList
     */
    public List<String> sorted(String[] strings) {
        TreeSet<String> treeSet = new TreeSet<>();
        Collections.addAll(treeSet, strings);
        List<String> list = new ArrayList<>();
        fillTreeSet(treeSet);
        list.addAll(treeSet);
        return list;

    }

    /**
     * Сортировка списка сотрудников по отделам по убвыванию.
     *
     * @param strings String[]
     * @return list ArrayList
     */
    public List<String> sortDesc(String[] strings) {
        TreeSet<String> treeSet = new TreeSet<>(new DescComparator());
        Collections.addAll(treeSet, strings);
        fillTreeSet(treeSet);
        List<String> list = new ArrayList<>();
        list.addAll(treeSet);
        return list;
    }

    /**
     * Заполнение пропусков в списке.
     *
     * @param set TreeSet
     */
    private void fillTreeSet(TreeSet<String> set) {
        TreeSet<String> copyTreeSet = new TreeSet<>();
        copyTreeSet.addAll(set);
        for (String element : copyTreeSet) {
            if (element.contains("\\")) {
                String[] words = element.split("\\\\");
                String word = words[0];
                set.add(word);
                for (int i = 1; i < words.length - 1; i++) {
                    word = word + "\\" + words[i];
                    set.add(word);
                }
            }
        }
    }

    /**
     * Comparator Desc.
     */
    private class DescComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            int result = 0;
            String[] line1 = o1.split("\\\\");
            int length1 = line1.length;
            String[] line2 = o2.split("\\\\");
            int length2 = line2.length;
            int min = Math.min(length1, length2);
            result = length1 - length2;
            for (int i = 0; i < min; i++) {
                if (!line1[i].equals(line2[i])) {
                    result = line2[i].compareTo(line1[i]);
                    break;
                }
            }
            return result;
        }
    }
}

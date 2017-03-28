package ru.agolovin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class CollectionsChronometer {


    /**
     * Capacity of the elements in the lists.
     * Check operation time to add/delete items to/from ArrayList, LinkedList, TreeSet.
     */
    private static final int CAPACITY = 200000;

    /**
     * Number of elements to remove.
     */
    private static final int REMOVE_CAPACITY = CAPACITY / 2;

    /**
     * Main method.
     *
     * @param args String
     */
    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        TreeSet<String> treeSet = new TreeSet<>();

        CollectionsChronometer chronometer = new CollectionsChronometer();



        System.out.println(String.format("Add to Linkedlist %s items", CAPACITY));
        System.out.println(String.format("Amount time: %s", chronometer.add(linkedList, "line", CAPACITY)));

        System.out.println(String.format("Delete from LinkedLIst %s items", REMOVE_CAPACITY));
        System.out.println(String.format("Amount time: %s", chronometer.delete(linkedList, REMOVE_CAPACITY)));

        System.out.println(String.format("Add to Arraylist %s items", CAPACITY));
        System.out.println(String.format("Amount time: %s", chronometer.add(arrayList, "line", CAPACITY)));

        System.out.println(String.format("Delete from ArrayList %s items", REMOVE_CAPACITY));
        System.out.println(String.format("Amount time: %s", chronometer.delete(arrayList, REMOVE_CAPACITY)));

        System.out.println(String.format("Add to TreeSet %s items", CAPACITY));
        System.out.println(String.format("Amount time: %s", chronometer.add(treeSet, "line", CAPACITY)));

        System.out.println(String.format("Delete from TreeSet %s items", REMOVE_CAPACITY));
        System.out.println(String.format("Amount time: %s", chronometer.delete(treeSet, REMOVE_CAPACITY)));

    }

    /**
     * Add line to collection.
     *
     * @param collection Collection
     * @param line       String
     * @param amount     int
     * @return operation time long.
     */
    public long add(Collection<String> collection, String line, int amount) {
        long startTime = getTime();
        for (int i = 0; i < amount; i++) {
            String s = line + i;
            collection.add(s);
        }
        long endTime = getTime();

        return endTime - startTime;
    }

    /**
     * Delete line from collection.
     *
     * @param collection Collection
     * @param amount     int
     * @return operation time long.
     */
    public long delete(Collection<String> collection, int amount) {
        long startTime = getTime();
        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext() && amount != 0) {
            iterator.next();
            iterator.remove();
            amount--;
        }
        long endTime = getTime();

        return endTime - startTime;
    }

    /**
     * Get current system time.
     *
     * @return Current time long
     */
    private long getTime() {
        return System.currentTimeMillis();
    }
}

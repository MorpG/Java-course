package ru.agolovin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class CollectionsChronometer {

    private static final int CAPACITY = 1000;

    private ArrayList<String> arrayList = new ArrayList<>();

    private LinkedList<String> linkedList = new LinkedList<String>();

    private TreeSet<String> treeSet = new TreeSet<>();

    private long startTime;

    private long endTime;

    public static void main(String[] args) {
        CollectionsChronometer chronometer = new CollectionsChronometer();
        chronometer.checkAddArrayList();
        chronometer.checkAddLinkedList();
        chronometer.checkAddTreeSet();
        chronometer.checkDeleteFromArrayList();
        chronometer.checkDeleteFromLinkedLIst();
        chronometer.checkDeleteFromTreeSet();
    }

    private void checkDeleteFromTreeSet() {
        System.out.println(String.format("Delete from TreeSet %s items", CAPACITY - 20));
        long amountTime = delete(treeSet, CAPACITY - 20);
        System.out.println(String.format("Amount time: %s", amountTime));
    }

    private void checkDeleteFromLinkedLIst() {
        System.out.println(String.format("Delete from LinkedLIst %s items", CAPACITY - 20));
        long amountTime = delete(linkedList, CAPACITY - 20);
        System.out.println(String.format("Amount time: %s", amountTime));
    }

    private void checkDeleteFromArrayList() {
        System.out.println(String.format("Delete from ArrayList %s items", CAPACITY - 20));
        long amountTime = delete(arrayList, CAPACITY - 20);
        System.out.println(String.format("Amount time: %s", amountTime));
    }

    private void checkAddArrayList() {
        System.out.println(String.format("Add to Arraylist %s items", CAPACITY));
        long amountTime = add(this.arrayList, "line", 100);
        System.out.println(String.format("Amount time: %s", amountTime));
    }

    private void checkAddLinkedList() {
        System.out.println(String.format("Add to Linkedlist %s items", CAPACITY));
        long amountTime = add(this.arrayList, "line", 100);
        System.out.println(String.format("Amount time: %s", amountTime));

    }

    private void checkAddTreeSet() {
        System.out.println(String.format("Add to TreeSet %s items", CAPACITY));
        long amountTime = add(treeSet, "line", 100);
        System.out.println(String.format("Amount time: %s", amountTime));
    }

    public long add(Collection<String> collection, String line, int amount) {
        this.startTime = getTime();
        for (int i = 0; i < amount; i++) {
            collection.add(line);
        }
        this.endTime = getTime();
        return this.endTime - this.startTime;
    }

    public long delete(Collection<String> collection, int amount) {
        this.startTime = getTime();
        for (int i = 0; i < amount; i++) {
            collection.remove(i);
        }
        this.endTime = getTime();

        return this.endTime - this.startTime;
    }

    private long getTime() {
        return System.nanoTime();
    }
}

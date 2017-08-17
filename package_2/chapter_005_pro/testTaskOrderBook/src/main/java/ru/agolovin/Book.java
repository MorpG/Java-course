package ru.agolovin;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Book {

    private Map<Long, Order> allOrder = new HashMap<>();

    private Map<Float, Order> sellBook = new TreeMap<>();

    private Map<Float, Order> buyBook = new TreeMap<>(new Comparator<Float>() {
        @Override
        public int compare(Float o1, Float o2) {
            return o2.compareTo(o1);
        }
    });

    void add(Order order) {
        allOrder.put(order.getId(), order);
    }

    ;

    void delete(Order order) {
        allOrder.remove(order.getId());
    }
}

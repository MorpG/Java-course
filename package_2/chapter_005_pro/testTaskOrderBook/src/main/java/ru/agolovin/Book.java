package ru.agolovin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

class Book {

    private Map<Integer, Order> orders = new HashMap<>();

    private Map<Float, Order> sellBook = new TreeMap<>();

    private Map<Float, Order> buyBook = new TreeMap<>(Comparator.reverseOrder());

    void add(Order order) {
        orders.put(order.getId(), order);
    }

    void delete(Integer orderId) {
        orders.remove(orderId);
    }

    private void replace() {
        for (Map.Entry<Integer, Order> element : orders.entrySet()) {
            if ("BUY".equals(element.getValue().getType())) {
                Order newOrder = buyBook.get(element.getValue().getPrice());
                if (newOrder != null) {
                    newOrder.setVolume(newOrder.getVolume()
                            + element.getValue().getVolume());
                } else {
                    buyBook.put(element.getValue().getPrice(), element.getValue());
                }
            } else {
                Order temp = sellBook.get(element.getValue().getPrice());
                if (temp != null) {
                    temp.setVolume(temp.getVolume() + element.getValue().getVolume());
                } else {
                    sellBook.put(element.getValue().getPrice(), element.getValue());
                }
            }
        }

    }

    private void work() {
        Iterator<Float> buyIterator = buyBook.keySet().iterator();
        Iterator<Float> sellIterator = sellBook.keySet().iterator();

        Float nextBuy = buyIterator.next();
        Float nextSell = sellIterator.next();

        List<Float> buyDel = new ArrayList<>();
        List<Float> sellDel = new ArrayList<>();

        boolean flag = false;

        do {
            Order buyOrder = buyBook.get(nextBuy);
            Order sellOrder = sellBook.get(nextSell);
            if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                if (buyOrder.getVolume() > sellOrder.getVolume()) {
                    buyOrder.setVolume(buyOrder.getVolume() - sellOrder.getVolume());
                    nextSell = sellIterator.next();
                    sellDel.add(buyOrder.getPrice());
                } else if (buyOrder.getVolume() < sellOrder.getVolume()) {
                    sellOrder.setVolume(sellOrder.getVolume() - buyOrder.getVolume());
                    nextBuy = buyIterator.next();
                    buyDel.add(buyOrder.getPrice());
                } else {
                    buyBook.remove(buyOrder.getPrice());
                    sellBook.remove(sellOrder.getPrice());
                    nextBuy = buyIterator.next();
                    nextSell = sellIterator.next();
                }
            } else {
                flag = true;
            }
        } while (!flag);

        for (Float element : buyDel) {
            buyBook.remove(element);
        }

        for (Float element : sellDel) {
            sellBook.remove(element);
        }
    }

    void init() {
        replace();
        work();
        Iterator<Float> buyIterator = buyBook.keySet().iterator();
        Iterator<Float> sellIterator = sellBook.keySet().iterator();

        while (buyIterator.hasNext() && sellIterator.hasNext()) {
            Float nextBuy = buyIterator.next();
            Float nextSell = sellIterator.next();

            Order buyOrder = buyBook.get(nextBuy);
            Order sellOrder = sellBook.get(nextSell);

            System.out.print(
                    String.format(
                            "%7s @ %s", buyOrder.getVolume(),
                            buyOrder.getPrice()));
            System.out.println(
                    String.format(
                            " - %s @ %s", sellOrder.getPrice(),
                            sellOrder.getVolume()));

        }

    }
}

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

    /**
     * All orders.
     */
    private Map<Integer, Order> orders = new HashMap<>();

    /**
     * sell book.
     */
    private Map<Float, Order> sellBook = new TreeMap<>();

    /**
     * buy book.
     */
    private Map<Float, Order> buyBook = new TreeMap<>(Comparator.reverseOrder());

    /**
     * Buy order.
     */
    private Order buyOrder;

    /**
     * sell order.
     */
    private Order sellOrder;

    /**
     * add order to map.
     *
     * @param order Order.
     */
    void add(Order order) {
        this.orders.put(order.getId(), order);
    }

    /**
     * delete order in the map by id.
     *
     * @param orderId int.
     */
    void delete(Integer orderId) {
        this.orders.remove(orderId);
    }

    /**
     * replace orders.
     */
    private void replace() {
        for (Map.Entry<Integer, Order> element : this.orders.entrySet()) {
            if ("BUY".equals(element.getValue().getType())) {
                Order newOrder = this.buyBook.get(element.getValue().getPrice());
                if (newOrder != null) {
                    newOrder.setVolume(newOrder.getVolume()
                            + element.getValue().getVolume());
                } else {
                    this.buyBook.put(element.getValue().getPrice(), element.getValue());
                }
            } else {
                Order temp = this.sellBook.get(element.getValue().getPrice());
                if (temp != null) {
                    temp.setVolume(temp.getVolume() + element.getValue().getVolume());
                } else {
                    this.sellBook.put(element.getValue().getPrice(), element.getValue());
                }
            }
        }

    }

    /**
     * set orders.
     */
    private void work() {
        Iterator<Float> buyIterator = this.buyBook.keySet().iterator();
        Iterator<Float> sellIterator = this.sellBook.keySet().iterator();

        Float nextBuy = buyIterator.next();
        Float nextSell = sellIterator.next();

        List<Float> buyDel = new ArrayList<>();
        List<Float> sellDel = new ArrayList<>();

        boolean flag = false;

        do {
            this.buyOrder = this.buyBook.get(nextBuy);
            this.sellOrder = this.sellBook.get(nextSell);
            if (this.buyOrder.getPrice() >= this.sellOrder.getPrice()) {
                if (this.buyOrder.getVolume() > this.sellOrder.getVolume()) {
                    this.buyOrder.setVolume(this.buyOrder.getVolume() - this.sellOrder.getVolume());
                    nextSell = sellIterator.next();
                    sellDel.add(this.sellOrder.getPrice());
                } else if (this.buyOrder.getVolume() < this.sellOrder.getVolume()) {
                    this.sellOrder.setVolume(this.sellOrder.getVolume() - this.buyOrder.getVolume());
                    nextBuy = buyIterator.next();
                    buyDel.add(this.buyOrder.getPrice());
                } else {
                    this.buyBook.remove(this.buyOrder.getPrice());
                    this.sellBook.remove(this.sellOrder.getPrice());
                    nextBuy = buyIterator.next();
                    nextSell = sellIterator.next();
                }
            } else {
                flag = true;
            }
        } while (!flag);

        this.buyBook = removeUnused(buyDel, this.buyBook);

        this.sellBook = removeUnused(sellDel, this.sellBook);
    }

    /**
     * removeunused orders.
     *
     * @param list List
     * @param book Map
     * @return Map
     */
    private Map<Float, Order> removeUnused(List<Float> list, Map<Float, Order> book) {
        for (Float element : list) {
            book.remove(element);
        }
        return book;
    }

    /**
     * initialization.
     */
    void init() {
        replace();
        work();
        Iterator<Float> buyIterator = this.buyBook.keySet().iterator();
        Iterator<Float> sellIterator = this.sellBook.keySet().iterator();

        Float nextBuy;
        Float nextSell;

        System.out.println("      BID             ASK");
        System.out.println("Volume @ Price - Volume @ Price");

        while (buyIterator.hasNext() && sellIterator.hasNext()) {
            nextBuy = buyIterator.next();
            nextSell = sellIterator.next();

            this.buyOrder = this.buyBook.get(nextBuy);
            this.sellOrder = this.sellBook.get(nextSell);

            System.out.print(
                    String.format(
                            "%7s @ %s", this.buyOrder.getVolume(),
                            this.buyOrder.getPrice()));
            System.out.println(
                    String.format(
                            " - %s @ %s", this.sellOrder.getVolume(),
                            this.sellOrder.getPrice()));
        }

        if (buyIterator.hasNext()) {
            do {
                this.buyOrder = this.buyBook.get(buyIterator.next());
                System.out.print(
                        String.format(
                                "%7s @ %s", this.buyOrder.getVolume(),
                                this.buyOrder.getPrice()));
                System.out.println(
                        String.format(
                                " - %6s", "------"));
            } while (buyIterator.hasNext());
        } else if (sellIterator.hasNext()) {
            do {
                this.sellOrder = this.sellBook.get(sellIterator.next());
                System.out.print(String.format("%14s", "------"));
                System.out.println(
                        String.format(
                                " - %s @ %s", this.sellOrder.getVolume(),
                                this.sellOrder.getPrice()));
            } while (sellIterator.hasNext());
        }
        System.out.println();
    }
}

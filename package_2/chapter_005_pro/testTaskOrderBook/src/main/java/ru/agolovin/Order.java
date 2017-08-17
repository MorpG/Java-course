package ru.agolovin;

import java.util.Objects;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Order {

    private long id;
    private int volume;
    private float price;
    private String type;

    Order(long id, int volume, float price, String type) {
        this.id = id;
        this.volume = volume;
        this.price = price;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                volume == order.volume &&
                Float.compare(order.price, price) == 0 &&
                Objects.equals(type, order.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, volume, price, type);
    }
}

package ru.agolovin;

import java.util.Objects;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Order {

    private int id;
    private int volume;
    private float price;
    private String type;

    Order(int id, int volume, float price, String type) {
        this.id = id;
        this.volume = volume;
        this.price = price;
        this.type = type;
    }

    int getId() {
        return id;
    }

    void setVolume(int volume) {
        this.volume = volume;
    }

    int getVolume() {
        return volume;
    }

    float getPrice() {
        return price;
    }

    String getType() {
        return type;
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

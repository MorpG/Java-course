package ru.agolovin;

import java.util.Objects;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Order {

    /**
     * Order id.
     */
    private int id;

    /**
     * order volume.
     */
    private int volume;

    /**
     * order price.
     */
    private float price;

    /**
     * order type.
     */
    private String type;

    /**
     * Constructor.
     *
     * @param id     int
     * @param volume int
     * @param price  float
     * @param type   String
     */
    Order(int id, int volume, float price, String type) {
        this.id = id;
        this.volume = volume;
        this.price = price;
        this.type = type;
    }

    /**
     * Get order Id.
     *
     * @return int Id
     */
    int getId() {
        return id;
    }

    /**
     * get order volume.
     *
     * @return int volume.
     */
    int getVolume() {
        return volume;
    }

    /**
     * Set order volume.
     *
     * @param volume int.
     */
    void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * get order price.
     *
     * @return float price.
     */
    float getPrice() {
        return price;
    }

    /**
     * get order type,
     *
     * @return string type
     */
    String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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

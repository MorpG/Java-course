package ru.agolovin.start;

import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

import java.util.Random;

/**
 * Tracker methods.
 * contains add, update, delete Item.
 * findById, generateId.
 * getAll, getByFilter.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */


public class Tracker {

    /**
     * @param size int
     */

    private final int size = 10;

    /**
     * @param size int
     */

    private Item[] items = new Item[size];

    /**
     * @param position int
     */

    private int position = 0;

    /**
     * @param RN Random
     */

    private static final Random RN = new Random();

    /**
     * add Item into tacker.
     *
     * @param item Item
     * @return item Item
     */

    public final Item add(final Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }

    /**
     * update Item in the tacker.
     *
     * @param item Item
     */


    public final void updateItem(final Item item) {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(item.getId())) {
                this.items[i] = item;
                break;
            }
        }
    }

    /**
     * delete Item in the tacker.
     *
     * @param item Item
     */

    public final void deleteItem(final Item item) {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(item.getId())) {
                this.items[i] = null;
                break;
            }
        }
    }

    /**
     * find Item by Id in the tacker.
     *
     * @param id String
     * @return item Item
     */

    final Item findById(final String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * generate new id for Tracker.
     *
     * @return id String
     */

    final String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * show all in Tracker.
     *
     * @return result Array of Items
     */

    public final Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int i = 0; i < this.position; i++) {
            result[i] = this.items[i];
        }
        return result;
    }

    /**
     * show by filter in Tracker.
     *
     * @param filter Filter
     * @return result Array of Items
     */

    public final Item[] getByFilter(final Filter filter) {
        Item[] tmp = new Item[this.position];
        int length = 0;
        for (int i = 0; i < this.position; i++) {
            if (items[i].getName().equals(filter.getFilter())
                    || items[i].getDescription().equals(filter.getFilter())) {
                tmp[i] = items[i];
                length++;
            }
        }
        Item[] result = new Item[length];
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                result[i] = tmp[i];
            }
        }

        return result;

    }

}

package ru.agolovin.start;

import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

import java.util.ArrayList;
import java.util.List;
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
     * RN Random.
     */
    private static final Random RN = new Random();

    /**
     * List Items.
     */
    private List<Item> items = new ArrayList<>();

    /**
     * add Item into tacker.
     *
     * @param item Item
     */
    final void addItem(final Item item) {
        item.setId(this.generateId());
        this.items.add(item);
    }

    /**
     * update Item in the tacker.
     *
     * @param item Item
     */
    final void updateItem(final Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (this.items.get(i).getId().equals(item.getId())) {
                this.items.set(i, item);
                break;
            }
        }
    }

    /**
     * delete Item in the tacker.
     *
     * @param item Item
     */
    final void deleteItem(final Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (this.items.get(i).getId().equals(item.getId())) {
                this.items.set(i, null);
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
        for (Item item : this.items) {
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
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * show all in Tracker.
     *
     * @return result Array of Items
     */
    final List<Item> getAll() {
        List<Item> result = new ArrayList<>();
        for (Item item : this.items) {
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * show by filter in Tracker.
     *
     * @param filter Filter
     * @return result Array of Items
     */
    final List<Item> getByFilter(final Filter filter) {

        List<Item> tmp = new ArrayList<>();
        int length = 0;
        for (Item item : this.items) {
            if (item != null
                    && ((item.getName().equals(filter.getFilter()))
                    || item.getDescription().equals(filter.getFilter()))) {
                tmp.add(item);
                length++;
            }
        }
        List<Item> result = new ArrayList<>();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                result.add(tmp.get(i));
            }
        }

        return result;
    }
}

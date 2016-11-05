package ru.agolovin.start;

import ru.agolovin.models.*;

import java.util.*;

public class Tracker {
    private Item[] items = new Item[10];
    private int position = 0;
    private static final Random RN = new Random();

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }

    public void updateItem (String id, Item updateItem) {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                this.items[i] = updateItem;
                break;
            }
        }
    }

    public void deleteItem(Item item) {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(item.getId())) {
                this.items[i] = null;
                break;
            }
        }
    }

    Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int i = 0; i < this.position; i++) {
            result[i] = this.items[i];
        }
        return result;
    }

    public Item[] getByFilter(Filter filter){
        Item[] tmp = new Item[this.position];
        int length = 0;
        for (int i = 0; i <this.position; i++) {
            if (items[i].getName().equals(filter.getFilter()) || items[i].description.equals(filter.getFilter())) {
                tmp[i] = items[i];
                length++;
            }
        }
        Item[] result = new Item[length];
        if (length > 0) {
            for (int i = 0; i < length; i++)
                result[i] = tmp[i];
        }

        return result;

    }

}
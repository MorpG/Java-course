package ru.agolovin.start;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import ru.agolovin.models.*;

public class TrackerTest{
    @Test
    public void whetAddNewItemThenResultIs(){
        Tracker tracker = new Tracker();
        Item item = new Item("testName", "testDescription", 1);
        Item[] result = new Item[1];
        tracker.add(item);
        result[0] = item;
        assertThat(tracker.getAll(), is(result));
    }

    @Test
    public void whenUpdateItemThenResultIs(){
        Tracker tracker = new Tracker();
        Item item = new Item("testName", "testDescription", 1);
        Item updateItem = new Item("updateName", "updateDescription", 2);
        Item[] result = new Item[1];
        tracker.add(item);
        tracker.updateItem(item.getId(), updateItem);
        result[0] = updateItem;
        assertThat(tracker.getAll(), is(result));
    }

    @Test
    public void whenDeleteItemThenResultIs(){
        Tracker tracker = new Tracker();
        Item itemOne = new Item("testName", "testDescription", 1);
        Item itemTwo = new Item("updateName", "updateDescription", 2);
        Item[] result = new Item[2];
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.deleteItem(itemOne);
        result[1] = itemTwo;
        assertThat(tracker.getAll(), is(result));
    }

    @Test
    public void whenFindByFilterThenResultIs(){
        Tracker tracker = new Tracker();
        Item itemOne = new Item("testName", "testDescription", 1);
        Item itemTwo = new Item("qwe", "sss", 2);
        Item[] result = new Item[1];
        tracker.add(itemOne);
        result[0] = itemOne;
        Filter filter = new Filter("testName");
        assertThat(tracker.getByFilter(filter), is(result));
    }

}
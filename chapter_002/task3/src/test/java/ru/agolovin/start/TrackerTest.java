package ru.agolovin.start;

import org.junit.Test;

import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test methods for Tracker.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {

    /**
     * Test for adding new Item in tracker.
     */
    @Test
    public final void whetAddNewItemThenResultIs() {
        Tracker tracker = new Tracker();
        Item item = new Item("testName", "testDescription", 1);
        Item[] result = new Item[1];
        tracker.add(item);
        result[0] = item;
        assertThat(tracker.getAll(), is(result));

    }

    /**
     * Test for update exist Item in tracker.
     */
    @Test
    public final void whenUpdateItemThenResultIs() {
        Tracker tracker = new Tracker();
        Item item = new Item("testName", "testDescription", 1);
        Item updateItem = new Item("updateName", "updateDescription", 2);
        Item[] result = new Item[1];
        tracker.add(item);
        updateItem.setId(tracker.findById(item.getId()).getId());
        tracker.updateItem(updateItem);
        result[0] = updateItem;
        assertThat(tracker.getAll(), is(result));

    }

    /**
     * Test for delete exist Item in tracker.
     */
    @Test
    public final void whenDeleteItemThenResultIs() {
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

    /**
     * Test for find by filter Item in tracker.
     */
    @Test
    public final void whenFindByFilterThenResultIs() {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("testName", "testDescription", 1);
        Item[] result = new Item[1];
        tracker.add(itemOne);
        result[0] = itemOne;
        Filter filter = new Filter("testName");
        assertThat(tracker.getByFilter(filter), is(result));

    }

}

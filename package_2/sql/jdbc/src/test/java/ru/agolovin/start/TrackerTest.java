package ru.agolovin.start;

import org.junit.Test;
import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

import java.util.ArrayList;
import java.util.List;

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
     * Tracker for tests.
     */
    private Tracker tracker = new Tracker();

    /**
     * List.
     */
    private List<Item> result = new ArrayList<>();

    /**
     * Test for adding new Item in tracker.
     */
    @Test
    public final void whetAddNewItemThenResultIs() {
        Item item = new Item("testName", "testDescription", 1);
        tracker.addItem(item);
        result.add(item);
        assertThat(tracker.getAll(), is(result));

    }

    /**
     * Test for update exist Item in tracker.
     */
    @Test
    public final void whenUpdateItemThenResultIs() {
        Item item = new Item("testName", "testDescription", 1);
        Item updateItem = new Item("updateName", "updateDescription", 2);
        tracker.addItem(item);
        updateItem.setId(tracker.findById(item.getId()).getId());
        tracker.updateItem(updateItem);
        result.add(updateItem);
        assertThat(tracker.getAll(), is(result));

    }

    /**
     * Test for delete exist Item in tracker.
     */
    @Test
    public final void whenDeleteItemThenResultIs() {
        Item itemOne = new Item("testName", "testDescription", 1);
        Item itemTwo = new Item("updateName", "updateDescription", 2);
        tracker.addItem(itemOne);
        tracker.addItem(itemTwo);
        tracker.deleteItem(itemOne);
        result.add(itemTwo);
        assertThat(tracker.getAll(), is(result));

    }

    /**
     * Test for find by filter Item in tracker.
     */
    @Test
    public final void whenFindByFilterThenResultIs() {
        Item itemOne = new Item("testName", "testDescription", 1);
        tracker.addItem(itemOne);
        result.add(itemOne);
        Filter filter = new Filter("testName");
        assertThat(tracker.getByFilter(filter), is(result));

    }

}

package ru.agolovin.start;


import org.junit.Test;
import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test methods for StartUI.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class StartUITest {

    /**
     * Test for paragraph 1 in menu StartUI.
     */

    @Test
    public final void whenUserSet1inMenuThenResultIs() {
        final long timeCreate = 15;
        Tracker tracker = new Tracker();
        String[] answer = {"1", "ss", "d", "15", "6"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);

        Item item = new Item("ss", "d", timeCreate);
        Item[] res = stUI.getTracker().getByFilter(new Filter("ss"));
        assertThat(res[0].getName(), is(item.getName()));
    }

    /**
     * Test for paragraph 2 in menu StartUI.
     */

    @Test
    public final void whenUserSet2inMenuThenResultIs() {
        final long timecreate1 = 9;
        final long timecreate2 = 8;
        Tracker tracker = new Tracker();
        Item item1 = new Item("ss", "desc", timecreate1);
        Item item2 = new Item("ss2", "dd2", timecreate2);
        tracker.add(item1);
        tracker.add(item2);
        String idItem = tracker.findById(item2.getId()).getId();
        String[] answer = {"2", idItem, "6"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        Item item = new Item("ss", "desc", timecreate1);
        Item[] res = stUI.getTracker().getByFilter(new Filter("ss"));
        assertThat(res[0].getName(), is(item.getName()));
    }

    /**
     * Test for paragraph 2 in menu StartUI.
     */

    @Test
    public final void whenUserSet3inMenuThenResultIs() {
        Tracker tracker = new Tracker();
        Item item = new Item("testName", "testDescription", 1);
        Item updateItem = new Item("updateName", "updateDescription", 2);
        Item[] result = new Item[1];
        tracker.add(item);
        updateItem.setId(tracker.findById(item.getId()).getId());
        String idItem = updateItem.getId();
        result[0] = updateItem;
        String[] answer = {
                "3", idItem, "updateName", "updateDescription", "2", "6"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        Item[] res =
                stUI.getTracker().getByFilter(new Filter("updateDescription"));
        assertThat(res[0].getName(), is(updateItem.getName()));
    }

    /**
     * Test for paragraph 4 in menu StartUI.
     */

    @Test
    public final void whenUserSet4inMenuThenResultIs() {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("testName", "testDescription", 1);
        Item itemTwo = new Item("updateName", "updateDescription", 2);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        String[] answer = {"4", "testName", "6"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        Item[] res = stUI.getTracker().getByFilter(new Filter("testName"));
        assertThat(res[0].getName(), is(itemOne.getName()));
    }

    /**
     * Test for paragraph 5 in menu StartUI.
     */

    @Test
    public final void whenUserSet5inMenuThenResultIs() {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("testName", "testDescription", 1);
        Item itemTwo = new Item("updateName", "updateDescription", 2);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        String[] answer = {"5", "6"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        Item[] res = stUI.getTracker().getAll();
        assertThat(res.length, is(tracker.getAll().length));
    }
}

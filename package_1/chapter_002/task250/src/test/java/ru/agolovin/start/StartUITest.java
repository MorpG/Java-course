package ru.agolovin.start;


import org.junit.Test;
import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
     * Test for paragraph 0 in menu StartUI(MenuTracker).
     */
    @Test
    public final void whenUserSet0inMenuThenResultIs() {
        final long timeCreate = 15;
        Tracker tracker = new Tracker();
        String[] answer = {"0", "ss", "d", "d", "15", "y"};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        boolean check = false;
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        if (out.toString().contains("Please, enter number")) {
            check = true;
        }

        Item item = new Item("ss", "d", timeCreate);
        Item[] res = stUI.getTracker().getByFilter(new Filter("ss"));
        assertThat(res[0].getName(), is(item.getName()));
        assertThat(check, is(true));
    }

    /**
     * Test for paragraph 1 in menu StartUI(MenuTracker).
     */
    @Test
    public final void whenUserSet1inMenuThenResultIs() {
        final int arrayLength = 2;
        Tracker tracker = new Tracker();
        Item itemOne = new Item("testName", "testDescription", 1);
        Item itemTwo = new Item("updateName", "updateDescription", 2);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        String[] answer = {"1", "y"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        Item[] res = stUI.getTracker().getAll();
        assertThat(res.length, is(arrayLength));
    }

    /**
     * Test for paragraph 2 in menu StartUI.
     */
    @Test
    public final void whenUserSet2inMenuThenResultIs() {
        Tracker tracker = new Tracker();
        Item item = new Item("testName", "testDescription", 1);
        Item updateItem = new Item("updateName", "updateDescription", 2);
        tracker.add(item);
        updateItem.setId(tracker.findById(item.getId()).getId());
        String idItem = updateItem.getId();
        String[] answer = {
                "2", idItem, "updateName", "updateDescription", "d", "2", "y"
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        boolean check = false;
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        if (out.toString().contains("Please, enter number")) {
            check = true;
        }
        Item[] res =
                stUI.getTracker().getByFilter(
                        new Filter("updateDescription"));
        assertThat(res[0].getName(), is(updateItem.getName()));
        assertThat(check, is(true));
    }

    /**
     * Test for paragraph 3 in menu StartUI.
     */
    @Test
    public final void whenUserSet3inMenuThenResultIs() {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("testName", "testDescription", 1);
        Item itemTwo = new Item("updateName", "updateDescription", 2);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        String[] answer = {"3", "testName", "y"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        Item[] res = stUI.getTracker().getByFilter(new Filter("testName"));
        assertThat(res[0].getName(), is(itemOne.getName()));
    }

    /**
     * Test for paragraph 4 in menu StartUI (MenuTracker).
     */
    @Test
    public final void whenUserSet4inMenuThenResultIs() {
        final long timeCreate1 = 9;
        final long timeCreate2 = 8;
        Tracker tracker = new Tracker();
        Item item1 = new Item("ss", "desc", timeCreate1);
        Item item2 = new Item("ss2", "dd2", timeCreate2);
        tracker.add(item1);
        tracker.add(item2);
        String idItem = tracker.findById(item2.getId()).getId();
        String[] answer = {"4", idItem, "y"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        Item item = new Item("ss", "desc", timeCreate1);
        Item[] res = stUI.getTracker().getByFilter(new Filter("ss"));
        assertThat(res[0].getName(), is(item.getName()));
    }

    /**
     * Test for paragraph 0 and 1 in menu StartUI(MenuTracker).
     */
    @Test
    public final void whenUserSet0And1inMenuThenResultIs() {
        final long timeCreate = 15;
        Tracker tracker = new Tracker();
        String[] answer = {"0", "ss", "d", "15", "n", "1", "y"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);

        Item item = new Item("ss", "d", timeCreate);
        Item[] res = stUI.getTracker().getByFilter(new Filter("ss"));
        assertThat(res[0].getName(), is(item.getName()));
    }
}

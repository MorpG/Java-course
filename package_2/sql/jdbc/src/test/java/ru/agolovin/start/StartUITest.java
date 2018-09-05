package ru.agolovin.start;

import org.junit.Test;
import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

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
     * Tracker for testes.
     */
    private final Tracker tracker = new Tracker();

    /**
     * OutPutStream.
     */
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Test for paragraph 0 in menu StartUI(MenuTracker).
     */
    @Test
    public final void whenUserSet0inMenuThenResultIs() {
        final long timeCreate = 15;

        String[] answer = {"0", "ss", "d", "d", "15", "y"};

        System.setOut(new PrintStream(out));
        boolean check = false;
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        if (out.toString().contains("Please, enter number")) {
            check = true;
        }
        Item item = new Item("ss", "d", timeCreate);
        List<Item> res = stUI.getTracker().getByFilter(new Filter("ss"));
        assertThat(res.get(0).getName(), is(item.getName()));
        assertThat(check, is(true));
    }

    /**
     * Test for paragraph 1 in menu StartUI(MenuTracker).
     */
    @Test
    public final void whenUserSet1inMenuThenResultIs() {
        final int arrayLength = 2;
        Item itemOne = new Item("testName", "testDescription", 1);
        Item itemTwo = new Item("updateName", "updateDescription", 2);
        String[] answer = {"1", "y"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        tracker.addItem(itemOne);
        tracker.addItem(itemTwo);
        List<Item> res = stUI.getTracker().getAll();
        assertThat(res.size(), is(arrayLength));
    }

    /**
     * Test for paragraph 2 in menu StartUI.
     */
//    @Test
    public final void whenUserSet2inMenuThenResultIs() {
        Item item = new Item("testName", "testDescription", 1);
        Item updateItem = new Item("updateName", "updateDescription", 2);

        updateItem.setId(tracker.findById(item.getId()).getId());
        String idItem = updateItem.getId();
        String[] answer = {
                "2", idItem, "updateName", "updateDescription", "d", "2", "y"
        };
        System.setOut(new PrintStream(out));
        boolean check = false;
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        tracker.addItem(item);
        if (out.toString().contains("Please, enter number")) {
            check = true;
        }
        List<Item> res =
                stUI.getTracker().getByFilter(
                        new Filter("updateDescription"));
        assertThat(res.get(0).getName(), is(updateItem.getName()));
        assertThat(check, is(true));
    }

    /**
     * Test for paragraph 3 in menu StartUI.
     */
//    @Test
    public final void whenUserSet3inMenuThenResultIs() {
        Item itemOne = new Item("testName", "testDescription", 1);
        Item itemTwo = new Item("updateName", "updateDescription", 2);
        String[] answer = {"3", "testName", "y"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        tracker.addItem(itemOne);
        tracker.addItem(itemTwo);
        List<Item> res = stUI.getTracker().getByFilter(new Filter("testName"));
        assertThat(res.get(0).getName(), is(itemOne.getName()));
    }

    /**
     * Test for paragraph 4 in menu StartUI (MenuTracker).
     */
//    @Test
    public final void whenUserSet4inMenuThenResultIs() {
        final long timeCreate1 = 9;
        final long timeCreate2 = 8;
        Item item1 = new Item("ss", "desc", timeCreate1);
        Item item2 = new Item("ss2", "dd2", timeCreate2);
        tracker.addItem(item1);
        tracker.addItem(item2);
        String idItem = tracker.findById(item2.getId()).getId();
        String[] answer = {"4", idItem, "y"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        Item item = new Item("ss", "desc", timeCreate1);
        List<Item> res = stUI.getTracker().getByFilter(new Filter("ss"));
        assertThat(res.get(0).getName(), is(item.getName()));
    }

    /**
     * Test for paragraph 0 and 1 in menu StartUI(MenuTracker).
     */
    @Test
    public final void whenUserSet0And1inMenuThenResultIs() {
        final long timeCreate = 15;
        String[] answer = {"0", "ss", "d", "15", "n", "1", "y"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init(tracker);
        Item item = new Item("ss", "d", timeCreate);
        List<Item> res = stUI.getTracker().getByFilter(new Filter("ss"));
        assertThat(res.get(0).getName(), is(item.getName()));
    }
}

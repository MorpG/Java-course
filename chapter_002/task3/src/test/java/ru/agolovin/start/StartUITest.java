package ru.agolovin.start;


import org.junit.Test;
import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test
    public void whenUserSet1inMenuThenResultIs() {
        String[] answer = {"1", "ss", "d", "15", "6"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init();
        Item item = new Item("ss", "d", 15);
        Item[] res = stUI.tracker.getByFilter(new Filter("ss"));
        assertThat(res[0].getName(), is(item.getName()));
    }

    @Test
    public void whenUserSet2inMenuThenResultIs() {
        String[] answer = {"1", "ss", "desc", "9", "1", "ss2", "dd2", "8"};
        StartUI stUI = new StartUI(new StubInput(answer));
        stUI.init();
        Item item = new Item("ss", "desc", 9);
        Item[] res = stUI.tracker.getByFilter(new Filter("ss"));
        assertThat(res[0].getName(), is(item.getName()));
    }
}

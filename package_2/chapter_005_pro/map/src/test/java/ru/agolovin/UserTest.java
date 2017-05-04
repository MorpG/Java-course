package ru.agolovin;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserTest {

    private Calendar calc = new GregorianCalendar();
    private String name = "Name";
    private int children = 2;

    /**
     * Test user class.
     */
    @Test
    public void thenAddUserThneGetInformation() {

        User user = new User(name, children, calc);
        assertThat(user.getName(), is(name));
        assertThat(user.getChildren(), is(children));
        assertThat(user.getBirthday(), is(calc));
    }


    /**
     * Test add user to map w/o override equals and hashcode.
     */
    @Test
    public void thenAddSameUserToMapThenItContainsOne() {
        User user1 = new User(name, children, calc);
        User user2 = new User(name, children, calc);
        Map<User, Object> map = new HashMap<>();
        Integer item = 1;
        map.put(user1, item);
        map.put(user2, item);

        System.out.println("Map contains: ");
        for (Map.Entry<User, Object> pair : map.entrySet()) {
            User key = pair.getKey();
            System.out.println(String.format("User name: %s", key.getName()));
            System.out.println(String.format("User child: %s", key.getChildren()));
        }

    }
}

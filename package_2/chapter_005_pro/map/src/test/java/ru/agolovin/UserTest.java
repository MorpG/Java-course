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

    /**
     * Calendar.
     */
    private Calendar calc = new GregorianCalendar();

    /**
     * Name.
     */
    private String name = "Name";

    /**
     * Children.
     */
    private int children = 2;

    /**
     * Test user class.
     */
    @Test
    public void thenAddUserThenGetInformation() {

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

    /**
     * Test User equals.
     */
    @Test
    public void thenUserEqualsThenResultTrue() {
        User user1 = new User(name, children, calc);
        User user2 = new User(name, children, calc);
        boolean res = user1.equals(user2);
        boolean res1 = user2.equals(user1);
        assertThat(res, is(true));
        assertThat(res1, is(true));
    }

    /**
     * Test user not equals.
     */
    @Test
    public void thenUserNotEqualsThenResultFalse() {
        User user1 = new User(name, children, calc);
        User user2 = new User(null, 5, null);
        int resHash = user2.hashCode();
        boolean res = user1.equals(user2);
        boolean res1 = user2.equals(user1);
        assertThat(res, is(false));
        assertThat(res1, is(false));
        assertThat(resHash, is(155));
    }

}

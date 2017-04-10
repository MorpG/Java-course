package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserTest {

    /**
     * Test when user1 equals user2.
     */
    @Test
    public void whenUserIsSameThenResultTrue() {
        User user1 = new User("Name1", 0);
        User user2 = new User("Name1", 0);
        boolean result = user1.equals(user2);
        assertThat(result, is(true));
    }

    /**
     * Test when user1 not equals user2.
     */
    @Test
    public void whenUserNotTheSameThenResultFalse() {
        User user1 = new User("Name1", 0);
        User user2 = new User("Name2", 1);
        boolean result = user1.equals(user2);
        assertThat(result, is(false));
    }

}
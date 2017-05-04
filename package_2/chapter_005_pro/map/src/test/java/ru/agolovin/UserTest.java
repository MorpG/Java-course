package ru.agolovin;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserTest {

    /**
     * Test user class.
     */
    @Test
    public void thenAddUserThneGetInformation() {
        Calendar calc = new GregorianCalendar();
        String name = "Name";
        int children = 2;
        User user = new User(name, children, calc);
        assertThat(user.getName(), is(name));
        assertThat(user.getChildren(), is(children));
        assertThat(user.getBirthday(), is(calc));
    }

}
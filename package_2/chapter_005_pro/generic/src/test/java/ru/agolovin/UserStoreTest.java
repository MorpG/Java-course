package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class UserStoreTest {

    /**
     * Test add user to store.
     */
    @Test
    public void whenAddUsersToStoreAndGetIt() {
        UserStore userStore = new UserStore(3);

        String idUser1 = "idOne";
        String idUser2 = "idTwo";

        User user1 = new User(idUser1);
        User user2 = new User(idUser2);

        userStore.add(user1);
        userStore.add(user2);

        assertThat(userStore.get(idUser1), is(user1));
        assertThat(userStore.get(idUser2), is(user2));
    }

    /**
     * Test update user.
     */
    @Test
    public void whenUpdateUserThenItUpdate() {
        UserStore userStore = new UserStore(3);
        String idUser1 = "idOne";
        String idUser2 = "idTwo";
        User user1 = new User(idUser1);
        User user2 = new User(idUser2);
        userStore.add(user1);
        userStore.update(idUser1, user2);
        assertThat(userStore.get(idUser2), is(user2));

    }

    /**
     * Test delete user.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenDeleteUser2FromStoreThen() {
        UserStore userStore = new UserStore(3);
        String idUser1 = "idOne";
        String idUser2 = "idTwo";
        User user1 = new User(idUser1);
        User user2 = new User(idUser2);
        userStore.add(user1);
        userStore.add(user2);
        userStore.delete(idUser2);
        assertEquals(userStore.get(idUser2), null);
    }
}
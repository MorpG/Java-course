package ru.agolovin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class SortUserTest {

    /**
     * Sort user by age.
     */
    @Test
    public void whenAddSomeUserToListThenGiveSortedTreeMap() {
        User user1 = new User("Name1", 2);
        User user2 = new User("Name2", 1);
        User user3 = new User("Name3", 0);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        SortUser sortUser = new SortUser();
        Set<User> result = sortUser.sort(users);

        Set<User> answer = new LinkedHashSet<>();
        answer.add(user3);
        answer.add(user2);
        answer.add(user1);

        assertThat(result, is(answer));
    }
}

package ru.agolovin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class SortUserTest {

    /**
     * User 1.
     */
    private User user1 = new User("Name11", 2);

    /**
     * User 2.
     */
    private User user2 = new User("Name", 1);

    /**
     * User 3.
     */
    private User user3 = new User("Name333", 0);

    /**
     * Test class.
     */
    private SortUser sortUser = new SortUser();

    /**
     * List users.
     */
    private List<User> users = new ArrayList<>();

    /**
     * Add users to list.
     * @param list list.
     */
    private void collectionsAdd(List<User> list) {
        list.add(user1);
        list.add(user2);
        list.add(user3);
    }

    /**
     * Sort user by age.
     */
    @Test
    public void whenAddSomeUserToListThenGiveSortedTreeMap() {
        collectionsAdd(users);

        Set<User> result = sortUser.sort(users);

        Set<User> answer = new LinkedHashSet<>();
        answer.add(user3);
        answer.add(user2);
        answer.add(user1);

        assertThat(result, is(answer));
    }

    /**
     * Sort List<Users> by hashCode.
     */
    @Test
    public void whenAddSomeUserToListThenSortItByHashCode() {
        collectionsAdd(users);
        List<User> result = sortUser.sortHash(users);

        List<User> answer = new ArrayList<>();
        collectionsAdd(answer);

        for (int i = 0; i < answer.size() - 1; i++) {
            for (int j = i + 1; j < answer.size(); j++) {
                if (answer.get(i).hashCode() > answer.get(j).hashCode()) {
                    User temp = answer.get(i);
                    answer.set(i, answer.get(j));
                    answer.set(j, temp);
                }
            }
        }

        assertThat(result, is(answer));
    }

    /**
     * Sort List<Users> by Name length.
     */
    @Test
    public void whenAddSomeUserToListThenSortItByNameLength() {
        collectionsAdd(users);
        List<User> result = sortUser.sortLength(users);

        List<User> answer = new ArrayList<>();
        collectionsAdd(answer);

        for (int i = 0; i < answer.size() - 1; i++) {
            for (int j = i + 1; j < answer.size(); j++) {
                if (answer.get(i).getName().length() > answer.get(j).getName().length()) {
                    User temp = answer.get(i);
                    answer.set(i, answer.get(j));
                    answer.set(j, temp);
                }
            }
        }
        assertThat(result, is(answer));
    }
}

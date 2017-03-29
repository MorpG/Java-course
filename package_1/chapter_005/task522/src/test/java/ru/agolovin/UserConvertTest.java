package ru.agolovin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Создать класс User с полями id, name, city.
 * Cоздать клаcc UserConvert.
 * В классе UserConvert написать метод public HashMap<Integer, User> process(List<User> list) {},
 * который принимает в себя список пользователей и конвертирует его в Map с ключом Integer id и
 * соответствующим ему User.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class UserConvertTest {

    /**
     * Test convert List<User> to HashMap<Integer, User>. Key - user.Id.
     *
     * @throws Exception Exception
     */
    @Test
    public void whenConvertUserListToHashMapThenItConvert() throws Exception {
        int index = 0;
        User user1 = new User(index++, "Name1", "City1");
        User user2 = new User(index++, "Name2", "City2");
        User user3 = new User(index, "Name3", "City3");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        UserConvert userConvert = new UserConvert();
        HashMap<Integer, User> result;
        result = userConvert.process(users);

        HashMap<Integer, User> answer = new HashMap<>();
        answer.put(user1.getId(), user1);
        answer.put(user2.getId(), user2);
        answer.put(user3.getId(), user3);


        assertThat(result, is(answer));

    }
}
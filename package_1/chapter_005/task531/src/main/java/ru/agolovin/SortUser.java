package ru.agolovin;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Необходимо создать модель User с полями name, age.
 * Класс User должен реализовать интерфейс Comparable.
 * В классе SortUser написать метод public Set<User> sort (List<User>),
 * который будет возвращать TreeSet пользователей, отсортированных
 * по возрасту в порядке возрастания.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class SortUser {

    /**
     * Add to TreeSet.
     *
     * @param list user.
     * @return Set<User>
     */
    public Set<User> sort(List<User> list) {
        Set<User> set = new TreeSet<>();
        set.addAll(list);
        return set;
    }

}

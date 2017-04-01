package ru.agolovin;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Необходимо создать модель User с полями name, age.
 * Класс User должен реализовать интерфейс Comparable.
 * В классе SortUser написать метод public Set<User> sort (List<User>),
 * который будет возвращать TreeSet пользователей, отсортированных
 * по возрасту в порядке возрастания.
 * <p>
 * В классе SortUser из 1-го задания необходимо написать два метода:
 * public List<User> sortHash (List<User>) - в этом методе необходимо определить
 * Comparator для метода Collections.sort и отсортировать List<User> по hash-коду.
 * public List<User> sortLength (List<User>) - в этом методе необходимо определить
 * Comparator для метода Collections.sort и отсортировать List<User> по длине имени.
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

    /**
     * Sort List<User> by hashCode.
     *
     * @param list list
     * @return List<User>
     */
    public List<User> sortHash(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.hashCode() - o2.hashCode();
            }
        });
        return list;
    }

    /**
     * Sort List<User> by name length.
     *
     * @param list list
     * @return List<User>
     */
    public List<User> sortLength(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        });
        return list;
    }

}

package ru.agolovin;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

@ThreadSafe
public class UserStorage {

    /**
     * UserStorage.
     */
    @GuardedBy("this")
    private HashMap<Integer, User> map = new HashMap<>();

    /**
     * add user to storage.
     *
     * @param user User
     */
    public void add(User user) {
        synchronized (this) {
            if (user != null) {
                map.put(user.getId(), user);
            }
        }
    }

    /**
     * Delete user from storage.
     *
     * @param user User
     */
    public void delete(User user) {
        synchronized (this) {
            if (user != null && map.containsKey(user.getId())) {
                map.remove(user.getId());
            }
        }
    }

    /**
     * Update user.
     *
     * @param user    User
     * @param newUser User
     */
    public void update(User user, User newUser) {
        synchronized (this) {
            if (map.containsKey(user.getId())) {
                map.replace(user.getId(), user, newUser);
            } else {
                map.put(newUser.getId(), newUser);
            }
        }
    }

    /**
     * Transfer user amount.
     *
     * @param fromId int
     * @param toId   int
     * @param amount int
     */
    public void transfer(int fromId, int toId, int amount) {
        synchronized (this) {
            if (map.containsKey(fromId) && map.containsKey(toId)) {
                if (map.get(fromId).getAmount() >= amount) {
                    User newUserAmount = map.get(toId);
                    newUserAmount.setAmount(newUserAmount.getAmount() + amount);
                    map.put(toId, newUserAmount);
                }
            }
        }
    }
}

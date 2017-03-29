package ru.agolovin;

import java.util.HashMap;
import java.util.List;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class UserConvert {

    /**
     * Convert List<user> to HashMap<Integer, User>. Key - user Id.
     *
     * @param list List<User>
     * @return HashMap<Integer, User>
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }
}

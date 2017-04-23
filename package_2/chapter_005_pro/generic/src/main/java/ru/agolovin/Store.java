package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 *
 * @param <T> extends Base
 */

public interface Store<T extends Base> {

    /**
     * Adding item to store.
     *
     * @param item T extends Base.
     */
    void add(T item);

    /**
     * Delete item.
     *
     * @param id T extends Base.
     */
    void delete(String id);

    /**
     * Update Item.
     *
     * @param id      String
     * @param newItem T extends Base
     */
    void update(String id, T newItem);

    /**
     * Get item by Id.
     *
     * @param id String.
     * @return Item T extend Base
     */
    T get(String id);
}

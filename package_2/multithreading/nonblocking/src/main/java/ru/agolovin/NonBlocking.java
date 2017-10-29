package ru.agolovin;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class NonBlocking {

    /**
     * Concurent map.
     */
    private final ConcurrentMap<Integer, Model> map = new ConcurrentHashMap<>();

    /**
     * add data.
     *
     * @param model Model
     */
    public void add(Model model) {
        this.map.put(model.getId(), model);
    }

    /**
     * delete data.
     *
     * @param model Model
     */
    public void delete(Model model) {
        this.map.remove(model.getId());
    }

    /**
     * Update data.
     *
     * @param newModel Model
     */
    public void update(Model newModel) {
        this.map.computeIfPresent(newModel.getId(), (integer, oldModel) -> {
            if (oldModel.getVersion() == newModel.getVersion()) {
                newModel.update();
                return newModel;
            } else {
                throw new OplimisticException("Busy by another thread");
            }
        });
    }
}

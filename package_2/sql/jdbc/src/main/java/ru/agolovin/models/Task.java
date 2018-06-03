package ru.agolovin.models;

/**
 * Task class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Task extends Item {

    /**
     * reload base Item method.
     *
     * @param name name
     * @param desc desc
     */
    public Task(final String name, final String desc) {
        this.setName(name);
        this.setDescription(desc);
    }
}

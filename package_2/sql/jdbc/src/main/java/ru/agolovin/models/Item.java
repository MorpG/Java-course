package ru.agolovin.models;

import java.util.Objects;

/**
 * Base Item methods.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Item {
    /**
     * variable class Item.
     *
     * id String.
     */
    private String id;

    /**
     * variable class Item.
     *
     * name String.
     */
    private String name;

    /**
     * variable class Item.
     *
     * description String.
     */
    private String description;

    /**
     * variable class Item.
     *
     * timeCreate long.
     */
    private long timeCreate;

    /**
     * base method Item.
     */
    Item() {
    }

    /**
     * base method Item.
     *
     * @param sName        name
     * @param sDescription description
     * @param sTimeCreate  timeCreate
     */
    public Item(
            final String sName,
            final String sDescription,
            final long sTimeCreate) {
        this.name = sName;
        this.description = sDescription;
        this.timeCreate = sTimeCreate;
    }

    /**
     * @return name
     */
    public final String getName() {
        return this.name;
    }

    /**
     * @param sName name
     */
    final void setName(final String sName) {
        this.name = sName;
    }

    /**
     * @return description
     */
    public final String getDescription() {
        return this.description;
    }

    /**
     * @param sDescription description
     */
    final void setDescription(final String sDescription) {
        this.description = sDescription;
    }

    /**
     * @return timeCreate
     */
    public final long getTimeCreate() {
        return this.timeCreate;
    }

    /**
     * @return id
     */
    public final String getId() {
        return this.id;
    }

    /**
     * @param sId id
     */
    public final void setId(final String sId) {
        this.id = sId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return timeCreate == item.timeCreate &&
                Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, timeCreate);
    }
}



package ru.agolovin.models;

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
     * @param id String
     */

    private String id;

    /**
     * variable class Item.
     *
     * @param name String
     */

    private String name;

    /**
     * variable class Item.
     *
     * @param description String
     */

    private String description;

    /**
     * variable class Item.
     *
     * @param timeCreate long
     */

    private long timeCreate;

    /**
     * base method Item.
     *
     */

    public Item() {
    }

    /**
     * base method Item.
     *
     * @param sName name
     * @param sDescription description
     * @param sTimeCreate timeCreate
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

    public final void setName(final String sName) {
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

    public final void setDescription(final String sDescription) {
        this.description = sDescription;
    }

    /**
     * @return timeCreate
     */

    public final long getTimeCreate() {
        return this.timeCreate;
    }

    /**
     * @param sTimeCreate timeCreate
     */

    public final void setTimeCreate(final long sTimeCreate) {
        this.timeCreate = sTimeCreate;
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
}

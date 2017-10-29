package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Model {

    /**
     * Model id.
     */
    private int id;

    /**
     * Model name.
     */
    private String name;

    /**
     * Model version.
     */
    private int version = 0;

    /**
     * Constructor.
     *
     * @param id   int
     * @param name String
     */
    public Model(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get id.
     *
     * @return id int.
     */
    public int getId() {
        return id;
    }

    /**
     * Get name.
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Get version.
     *
     * @return int version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Update version
     */
    public void update() {
        this.version++;
    }
}

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
    int getId() {
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
    int getVersion() {
        return version;
    }

    /**
     * Update version
     */
    void update() {
        this.version++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Model model = (Model) o;

        if (id != model.id) {
            return false;
        }
        if (version != model.version) {
            return false;
        }
        return name.equals(model.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + version;
        return result;
    }
}

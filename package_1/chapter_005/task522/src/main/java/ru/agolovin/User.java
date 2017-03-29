package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class User {

    /**
     * Identification.
     */
    private int id = 0;

    /**
     * User name.
     */
    private String name;

    /**
     * User city.
     */
    private String city;

    /**
     * Constructor.
     *
     * @param id int.
     * @param name String.
     * @param city String.
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * getter Id.
     *
     * @return id int
     */
    public int getId() {
        return this.id;
    }
}



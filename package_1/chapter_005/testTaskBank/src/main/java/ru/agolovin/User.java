package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class User {

    /**
     * User name.
     */
    private String name;

    /**
     * Passport id.
     */
    private int passport;

    /**
     * Constructor.
     *
     * @param name     String
     * @param passport int
     */
    User(String name, int passport) {

        this.name = name;
        this.passport = passport;
    }
}

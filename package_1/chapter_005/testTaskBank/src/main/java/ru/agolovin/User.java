package ru.agolovin;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return passport == user.passport
                && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }
}

package ru.agolovin;

import java.util.Calendar;

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
     * Qunat user child.
     */
    private int children;
    /**
     * User birthday.
     */
    private Calendar birthday;

    /**
     * Constructor.
     *
     * @param name     String
     * @param children int
     * @param birthday Calendar
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }


    /**
     * Get user name.
     *
     * @return name String.
     */
    public String getName() {
        return name;
    }

    /**
     * Get user childs.
     *
     * @return child int
     */
    public int getChildren() {
        return children;
    }

    /**
     * Get user birthday.
     *
     * @return birthday Calendar
     */
    public Calendar getBirthday() {
        return birthday;
    }

}

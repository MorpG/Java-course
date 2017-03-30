package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class User implements Comparable<User> {

    /**
     * User name.
     */
    private String name;

    /**
     * User age.
     */
    private int age;

    /**
     * Constructor.
     *
     * @param name String.
     * @param age  String.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Getter Name.
     *
     * @return name String.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter age.
     *
     * @return age int.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Compare user by ascending age.
     *
     * @param user User
     * @return 1 if older, -1 if younger, 0 - if same.
     */
    @Override
    public int compareTo(User user) {
        return Integer.compare(this.age, user.age);
    }
}



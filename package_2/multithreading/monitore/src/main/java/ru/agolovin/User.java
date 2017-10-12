package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class User {

    /**
     * User id.
     */
    private int id;

    /**
     * amount user.
     */
    private int amount;

    /**
     * Class constructor.
     *
     * @param id     int
     * @param amount int.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Get id.
     *
     * @return id int
     */
    int getId() {
        return id;
    }

    /**
     * get amount.
     *
     * @return amount int
     */
    int getAmount() {
        return amount;
    }

    /**
     * set amount.
     *
     * @param amount int.
     */
    void setAmount(int amount) {
        this.amount = amount;
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
        return id == user.id && amount == user.amount;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + amount;
        return result;
    }
}

package ru.agolovin;

import java.util.Objects;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Account {

    /**
     * Account value.
     */
    private double value;

    /**
     * Account requisites.
     */
    private String requisites;

    /**
     * Constructor.
     *
     * @param value      double.
     * @param requisites String.
     */
    Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Get account value.
     *
     * @return double result.
     */
    double getValue() {
        return value;
    }

    /**
     * Set value.
     *
     * @param value double.
     */
    void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Double.compare(account.value, value) == 0
                && Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, requisites);
    }
}

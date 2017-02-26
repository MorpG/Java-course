package ru.agolovin.server;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
abstract class BaseAction {

    /**
     * String name.
     */
    private String name;

    /**
     * @param sName String
     */
    BaseAction(String sName) {
        this.name = sName;
    }

    /**
     * Key.
     * @return key String
     */
    abstract String key();

    /**
     * @return info about menu line
     */
    String info() {
        return String.format(" %s. %s", this.key(), this.name);
    }

    /**
     * Action.
     */
    abstract void execute();

}

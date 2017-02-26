package ru.agolovin.server;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class BaseAction {

    /**
     * String name.
     */
    private String name;

    /**
     * @param sName String
     */
    public BaseAction(String sName) {
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
    public String info() {
        return String.format(" %s. %s", this.key(), this.name);
    }

    /**
     * Action.
     * @param text String
     */
    abstract void execute(String text);

}

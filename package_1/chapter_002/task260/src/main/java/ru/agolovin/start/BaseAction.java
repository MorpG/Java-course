package ru.agolovin.start;

/**
 * Abstract class for UserAction.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class BaseAction implements UserAction {

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
     * @return key value int
     */
    public abstract int key();

    /**
     * @param input   Input
     * @param tracker Tracker
     */
    public abstract void execute(Input input, Tracker tracker);

    /**
     * @return info about menu line
     */
    public String info() {
        return String.format(" %s. %s", this.key(), this.name);
    }
}

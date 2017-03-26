package ru.agolovin.start;

/**
 * interface for user action.
 */
public interface UserAction {

    /**
     * key integer.
     *
     * @return key int
     */
    int key();

    /**
     * @param input   Input
     * @param tracker Tracker
     */
    void execute(Input input, Tracker tracker);

    /**
     * menu line.
     *
     * @return menu line
     */
    String info();
}

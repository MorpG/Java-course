package ru.agolovin.start;

/**
 * Methods for User Interface.
 * Contains initialization, user menu.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public final class StartUI {

    /**
     * tracker Tracker.
     */
    private Tracker tracker = new Tracker();

    /**
     * input Input.
     */
    private Input input;

    /**
     * Base method.
     *
     * @param sInput Input
     */
    StartUI(final Input sInput) {
        this.input = sInput;
    }

    /**
     * main method.
     *
     * @param args String[]
     */
    public static void main(final String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ConsoleInput();
        new StartUI(input).init(tracker);
    }

    /**
     * Initialization.
     *
     * @param nTracker Tracker
     */
    void init(final Tracker nTracker) {
        this.tracker = nTracker;
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillActions();
        do {
            menu.show();
            int key = Integer.parseInt(input.ask("Select: "));
            menu.select(key);
        } while (!"y".equals(this.input.ask("Exit? y ")));
    }

    /**
     * @return tracker
     */
    Tracker getTracker() {
        return this.tracker;
    }
}

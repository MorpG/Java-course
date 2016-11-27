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
    private StartUI(final Input sInput) {
        this.input = sInput;
    }

    /**
     * main method.
     *
     * @param args String[]
     */
    public static void main(final String[] args) {
        Input input = new ConsoleInput();
        new StartUI(input).init();
    }

    /**
     * Initialization.
     */
    private void init() {
        Tracker sTracker = new Tracker();
        MenuTracker menu = new MenuTracker(this.input, sTracker);
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
    public Tracker getTracker() {
        return this.tracker;
    }
}

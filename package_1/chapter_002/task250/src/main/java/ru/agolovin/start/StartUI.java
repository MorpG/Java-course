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
     * len user menu.
     */
    private int len;
    /**
     * Array of possible range for input.
     */
    private int[] ranges = fillRanges();

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
        Input input = new ValidateInput();
        new StartUI(input).init(tracker);
    }

    /**
     * Initialization.
     *
     * @param nTracker Tracker
     */
    void init(final Tracker nTracker) {
        this.tracker = nTracker;
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        len = menu.getLengthUserActions();
        ranges = fillRanges();
        do {
            menu.show();
            menu.select(input.ask("Select: ", ranges));
        } while (!"y".equals(this.input.ask("Exit? y ")));

    }

    /**
     * @return tracker
     */
    Tracker getTracker() {
        return this.tracker;
    }

    /**
     * @return array of length user menu
     */
    private int[] fillRanges() {

        int ln = len;
        int[] array = new int[ln];
        for (int i = 0; i < ln; i++) {
            array[i] = i;
        }
        return array;
    }
}

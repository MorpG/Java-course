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
     * Array of possible range for input.
     */
    private final int lineZ = 0;
    /**
     * Array of possible range for input.
     */
    private final int lineO = 1;
    /**
     * Array of possible range for input.
     */
    private final int lineTw = 2;
    /**
     * Array of possible range for input.
     */
    private final int lineTh = 3;
    /**
     * Array of possible range for input.
     */
    private final int lineFo = 4;
    /**
     * Array of possible range for input.
     */
    private final int lineFv = 5;
    /**
     * Array of possible range for input.
     */
    private int[] ranges = new int[]{lineZ, lineO, lineTw, lineTh, lineFo, lineFv};

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
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillActions();
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
}

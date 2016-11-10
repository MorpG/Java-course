package ru.agolovin.start;

import ru.agolovin.models.Task;

/**
 * Prepare for UI.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public final class StartUI {

    /**
     * base method Item.
     */

    protected StartUI() {
    }

    /**
     * variable class Item.
     *
     * @param args args
     */

    public static void main(final String[] args) {
		ConsoleInput input = new ConsoleInput();
		Spring name = input.ask("Enter smth");
        Tracker tracker = new Tracker();
        tracker.add(new Task(name, "first desc"));
    }
}

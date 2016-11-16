package ru.agolovin.start;

import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

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
     * @param tracker Tracker
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
        new StartUI(new ConsoleInput()).init(tracker);
    }

    /**
     * Initialization.
     *
     *@param sTracker Tracker
     */

    void init(final Tracker sTracker) {
        this.tracker = sTracker;
        boolean flag = true;
        while (flag) {
            showMenu();
            int number = Integer.parseInt(input.ask("Enter the number:"));
            int position = 1;
            if (number == position++) {
                addMenu();
            } else if (number == position++) {
                delMenu();
            } else if (number == position++) {
                updMenu();
            } else if (number == position++) {
                fndByFilterMenu();
            } else if (number == position++) {
                getAllMenu();
            } else if (number == position++) {
                flag = false;
            } else {
                position = 1;
                System.out.println("Number out of range");
                System.out.println("Please repeat/n");
            }
        }
    }

    /**
     * implementation paragraph menu Add.
     */

    private void addMenu() {
        tracker.add(new Item(input.ask("Enter name"),
                input.ask("Enter description"),
                Long.parseLong(input.ask("Enter time create"))));
    }

    /**
     * implementation paragraph menu Delete.
     */

    private void delMenu() {
        String id = input.ask("Enter id to delete: ");
        tracker.deleteItem(tracker.findById(id));
    }

    /**
     * implementation paragraph menu Update.
     */

    private void updMenu() {
        String id = input.ask("Enter id to update: ");
        Item updateItem = new Item(input.ask("Enter name: "),
                input.ask("Enter description"),
                Long.parseLong(input.ask("Enter time create: ")));
        updateItem.setId(tracker.findById(id).getId());
        tracker.updateItem(updateItem);
    }

    /**
     * implementation paragraph menu Find by filter.
     */

    private void fndByFilterMenu() {
        Filter filter = new Filter(input.ask("Enter filter: "));
        Item[] result = tracker.getByFilter(filter);
        for (Item item : result) {
            System.out.println("Id: " + item.getId() + " Name: "
                    + item.getName() + " Description: "
                    + item.getDescription() + " Time create: "
                    + item.getTimeCreate());
        }
    }

    /**
     * implementation paragraph menu Get all.
     */

    private void getAllMenu() {
        Item[] result = tracker.getAll();
        for (Item item : result) {
            System.out.println("Id: " + item.getId() + " Name: "
                    + item.getName() + " Description: "
                    + item.getDescription() + " Time create: "
                    + item.getTimeCreate());
        }
    }

    /**
     * Menu.
     */

    private void showMenu() {
        System.out.println("1. Add");
        System.out.println("2. Delete");
        System.out.println("3. Update");
        System.out.println("4. Find by filter");
        System.out.println("5. Get all");
        System.out.println("6. Exit");
    }

    /**
    * Accessor methods for tracker.
    *
    * @return tracker Tracker
    */
    public Tracker getTracker() {
        return this.tracker;
    }
}

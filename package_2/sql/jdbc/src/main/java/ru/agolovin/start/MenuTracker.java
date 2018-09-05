package ru.agolovin.start;

import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Method to edit Item from tracker.
 */
class EditItem extends BaseAction {

    /**
     * Extends info from BaseAction.
     */
    public EditItem() {
        super("Edit item");
    }

    /**
     * @return 2 int key
     */
    public int key() {
        return 2;
    }

    /**
     * @param input   Input
     * @param tracker Tracker
     */
    public void execute(final Input input, final Tracker tracker) {
        String id = input.ask("Enter id to edit: ");
        String name = input.ask("Enter the task`s name: ");
        String desc = input.ask("Enter the task`s description: ");
        long timeCreate = MenuTracker.setTimeCreate(input);
        Item item = new Item(name, desc, timeCreate);
        item.setId(id);
//        tracker.updateItem(item);
    }

}

/**
 * Main methods for User Menu.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
class MenuTracker {

    /**
     * tracker Tracker.
     */
    private final Tracker tracker;
    /**
     * input Input.
     */
    private Input input;
    /**
     * actions User actions array.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * @param sInput   Input
     * @param sTracker Tracker
     */
    public MenuTracker(final Input sInput, final Tracker sTracker) {
        this.input = sInput;
        this.tracker = sTracker;
    }

    /**
     * Set time create.
     *
     * @param sInput Input
     * @return long TimeCreate
     */
    public static long setTimeCreate(final Input sInput) {
        long timeCreate = 0;
        boolean flag = true;
        do {
            try {
                timeCreate = Long.parseLong(sInput.ask("Enter time create: "));
                flag = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please, enter number");
            }
        } while (flag);
        return timeCreate;
    }

    /**
     * Actions for menu.
     */
    public void fillActions() {
        this.actions.add(new AddItem());
        this.actions.add(new MenuTracker.ShowItems());
        this.actions.add(new EditItem());
        this.actions.add(new FilterItem());
        this.actions.add(new DeleteItem());
        this.actions.add(new ExitMenu());
    }

    /**
     * @param key int
     */
    public void select(final int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Method for show menu.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * class for show Item.
     */
    private static class ShowItems extends BaseAction {

        /**
         * Extends info from BaseAction.
         */
        public ShowItems() {
            super("Show all items");
        }

        /**
         * key for menu.
         *
         * @return 1
         */
        public int key() {
            return 1;
        }

        /**
         * @param input   Input
         * @param tracker Tracker
         */
        public void execute(final Input input, final Tracker tracker) {
            for (Item item : tracker.getAll()) {
                System.out.print(
                        String.format(
                                "Id: %s. Name: %s. ",
                                item.getId(), item.getName()
                        )
                );
                System.out.println(
                        String.format(
                                "Description: %s. Time create: %s",
                                item.getDescription(), item.getTimeCreate())
                );
            }
        }
    }

    /**
     * Method add Item.
     */
    private class AddItem extends BaseAction {

        /**
         * Extends info from BaseAction.
         */
        public AddItem() {
            super("Add the new item");
        }

        /**
         * @return key 0
         */
        public int key() {
            return 0;
        }

        /**
         * @param sInput   Input
         * @param sTracker Tracker
         */
        public void execute(final Input sInput, final Tracker sTracker) {
            String name = sInput.ask("Enter the task`s name: ");
            String desc = sInput.ask("Enter the task`s description: ");
            long timeCreate = setTimeCreate(input);
            sTracker.addItem(new Item(name, desc, timeCreate));
        }
    }

    /**
     * Filter method for menu.
     */
    private class FilterItem extends BaseAction {

        /**
         * Extends info from BaseAction.
         */
        FilterItem() {
            super("Get by filter");
        }

        /**
         * @return keyThree
         */
        public int key() {
            return 3;
        }

        /**
         * @param sInput   Input
         * @param sTracker Tracker
         */
        public void execute(final Input sInput, final Tracker sTracker) {
            Filter filter = new Filter(
                    sInput.ask("Enter filter description: ")
            );
            List<Item> result = sTracker.getByFilter(filter);
            for (Item item : result) {
                System.out.println("Id: " + item.getId() + " Name: "
                        + item.getName() + " Description: "
                        + item.getDescription() + " Time create: "
                        + item.getTimeCreate());
            }
        }
    }

    /**
     * Delete methods.
     */
    private class DeleteItem extends BaseAction {

        /**
         * Extends info from BaseAction.
         */
        DeleteItem() {
            super("Delete Item");
        }

        /**
         * @return keyFour int
         */
        public int key() {
            return 4;
        }

        /**
         * @param sInput   Input
         * @param sTracker Tracker
         */
        public void execute(final Input sInput, final Tracker sTracker) {
            String id = sInput.ask("Enter id to delete");
            sTracker.deleteItem(sTracker.findById(id));
        }
    }

    /**
     * Exit method.
     */
    private class ExitMenu extends BaseAction {

        /**
         * Extends info from BaseAction.
         */
        ExitMenu() {
            super("Exit");
        }

        /**
         * @return keyFive int
         */
        public int key() {
            return 5;
        }

        /**
         * @param sInput   Input
         * @param sTracker Tracker
         */
        public void execute(final Input sInput, final Tracker sTracker) {

        }
    }

}

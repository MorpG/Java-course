package ru.agolovin.start;

import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

/**
 * Method to edit Item from tracker.
 */
class EditItem extends BaseAction {

    /**
     * Extends info from BaseAction.
     */
    EditItem() {
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
        boolean flag = true;
        long timeCreate = 0;
        String id = input.ask("Enter id to edit: ");
        String name = input.ask("Enter the task`s name: ");
        String desc = input.ask("Enter the task`s description: ");

        do {
            try {
                timeCreate = Long.parseLong(input.ask("Enter time create: "));
                flag = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please, enter number");
            }
        } while (flag);
        Item item = new Item(name, desc, timeCreate);
        item.setId(id);
        tracker.updateItem(item);
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
     * size userActions array.
     */
    private final int lengthUserActions = 6;

    /**
     * first length menu.
     */
    private int position = 0;

    /**
     * input Input.
     */
    private Input input;

    /**
     * tracker Tracker.
     */
    private Tracker tracker;

    /**
     * actions User actions array.
     */
    private UserAction[] actions = new UserAction[lengthUserActions];

    /**
     * @param sInput   Input
     * @param sTracker Tracker
     */
    MenuTracker(final Input sInput, final Tracker sTracker) {
        this.input = sInput;
        this.tracker = sTracker;
    }

    /**
     * Actions for menu.
     */
    void fillActions() {
        this.actions[position++] = this.new AddItem();
        this.actions[position++] = new MenuTracker.ShowItems();
        this.actions[position++] = new EditItem();
        this.actions[position++] = this.new FilterItem();
        this.actions[position++] = this.new DeleteItem();
        this.actions[position++] = this.new ExitMenu();
    }

    /**
     * @param key int
     */
    void select(final int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Method for show menu.
     */
    void show() {
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
        ShowItems() {
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
        AddItem() {
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
            boolean flag = true;
            long timeCreate = 0;
            String name = sInput.ask("Enter the task`s name: ");
            String desc = sInput.ask("Enter the task`s description: ");
            do {
                try {
                    timeCreate = Long.parseLong(sInput.ask("Enter time create: "));
                    flag = false;
                } catch (NumberFormatException nfe) {
                    System.out.println("Please, enter number");
                }
            } while (flag);
            sTracker.add(new Item(name, desc, timeCreate));
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
            final int keyThree = 3;
            return keyThree;
        }

        /**
         * @param sInput   Input
         * @param sTracker Tracker
         */
        public void execute(final Input sInput, final Tracker sTracker) {
            Filter filter = new Filter(
                    sInput.ask("Enter filter description: ")
            );
            Item[] result = sTracker.getByFilter(filter);
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
            final int keyFour = 4;
            return keyFour;
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
            final int keyFive = 5;
            return keyFive;
        }

        /**
         * @param sInput   Input
         * @param sTracker Tracker
         */
        public void execute(final Input sInput, final Tracker sTracker) {

        }
    }

}

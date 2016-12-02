package ru.agolovin.start;

import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

/**
 * Method to edit Item from tracker.
 */
class EditItem implements UserAction {

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
        long timeCreate = Long.parseLong(input.ask("Enter time create: "));
        Item item = new Item(name, desc, timeCreate);
        item.setId(id);
        tracker.updateItem(item);
    }

    /**
     * @return menu line
     */
    public String info() {
        return String.format(" %s. %s", this.key(), "Edit Item");
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
     *
     * @return lengthUserActions int
     */
    public int getLengthUserActions() {
        return this.lengthUserActions;
    }

    /**
     * Actions for menu.
     */
    void fillActions() {
        final int menuLineZero = 0;
        final int menuLineOne = 1;
        final int menuLineTwo = 2;
        final int menuLineThree = 3;
        final int menuLineFour = 4;
        final int menuLineFive = 5;
        this.actions[menuLineZero] = this.new AddItem();
        this.actions[menuLineOne] = new MenuTracker.ShowItems();
        this.actions[menuLineTwo] = new EditItem();
        this.actions[menuLineThree] = this.new FilterItem();
        this.actions[menuLineFour] = this.new DeleteItem();
        this.actions[menuLineFive] = this.new ExitMenu();
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
    private static class ShowItems implements UserAction {

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

        /**
         * @return menu line
         */
        public String info() {
            return String.format(" %s. %s", this.key(), "Show all items");
        }
    }

    /**
     * Method add Item.
     */
    private class AddItem implements UserAction {
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
            long timeCreate = Long.parseLong(sInput.ask("Enter time create: "));
            sTracker.add(new Item(name, desc, timeCreate));
        }

        /**
         * @return menu line
         */
        public String info() {
            return String.format(" %s. %s", this.key(), "Add the new item");
        }
    }

    /**
     * Filter method for menu.
     */
    private class FilterItem implements UserAction {
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

        /**
         * @return menu Line
         */
        public String info() {
            return String.format(" %s. %s", this.key(), "Get by filter");
        }
    }

    /**
     * Delete methods.
     */
    private class DeleteItem implements UserAction {
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

        /**
         * @return menu line
         */
        public String info() {
            return String.format(" %s. %s", this.key(), "Delete Item");
        }
    }

    /**
     * Exit method.
     */
    private class ExitMenu implements UserAction {

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

        /**
         * @return menu line
         */
        public String info() {
            return String.format(" %s. %s", this.key(), "Exit");
        }
    }

}

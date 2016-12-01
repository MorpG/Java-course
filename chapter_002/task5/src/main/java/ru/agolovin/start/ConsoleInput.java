package ru.agolovin.start;

import java.util.Scanner;

/**
 * Method to input form console.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {

    /**
     * scanner System input.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * @param question String
     * @return ask User answer
     */
    public final String ask(final String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * @param question String
     * @param range    int
     * @return User answer
     */
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range");
        }
    }
}

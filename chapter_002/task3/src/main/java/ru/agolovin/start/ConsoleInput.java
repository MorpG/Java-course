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
* @param scanner System input
*/

    private Scanner scanner = new Scanner(System.in);

    /**
    * @param question String
    *
    * @return ask User answer
    */

    public final String ask(final String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}

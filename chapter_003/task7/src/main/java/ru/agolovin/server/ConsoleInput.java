package ru.agolovin.server;

import java.util.Scanner;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {

    /**
     * Scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * User input.
     * @param question String
     * @return user input String
     */
    @Override
    public String ask(String question) {
        return scanner.nextLine();
    }
}

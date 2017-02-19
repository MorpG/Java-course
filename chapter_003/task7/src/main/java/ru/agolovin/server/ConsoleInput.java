package ru.agolovin.server;

import java.util.Scanner;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput {

    private String name;

    private String control;

    public ConsoleInput(String comand) {
        Scanner scanner = new Scanner(comand);
        if (scanner.hasNext()) {
            this.name = scanner.nextLine();
            if (scanner.hasNext()) {
                this.control = scanner.nextLine();

            }

        }
    }

    public String getName() {
        return name;
    }

    public String getControl() {
        return control;
    }
}

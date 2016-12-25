package ru.agolovin;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Check number in input stream class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

class CheckNumber {

    /**
     * Check number in input stream is even number.
     * @param in InputStream
     * @return boolean result
     */
    boolean isNumber(InputStream in) {
        boolean result = false;
        try {
            Scanner sc = new Scanner(in);
            int number = sc.nextInt();
            if (number % 2 == 0) {
                result = true;
            }
        } catch (InputMismatchException ime) {
            System.out.println(ime);
        }
        return result;
    }
}

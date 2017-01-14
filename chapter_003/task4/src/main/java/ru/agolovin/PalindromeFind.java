package ru.agolovin;

import java.util.Scanner;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class PalindromeFind {

    /**
     * Scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Method return user input.
     * @return user input String
     */
    public String ask() {
        System.out.println("Введите слово из пяти букв:");
        return scanner.nextLine();
    }

    /**
     * Check word for palindrome.
     * @return true if palindrome
     */
    public boolean isPalindrome() {

        final byte wordLength = 5;

        boolean result = false;

        char[] subString = ask().toLowerCase().toCharArray();

        if (subString.length != wordLength) {
            System.out.println("Количество букв не равно пяти");
            result = false;
        } else {

            for (int i = 0; i < wordLength / 2; i++) {
                if (subString[i] == subString[wordLength - i - 1]) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
        }
        if (result) {
            System.out.println("Введенное слово палиндром");
        } else {
            System.out.println("Введенное слово не палиндром");
        }
        return result;
    }

}


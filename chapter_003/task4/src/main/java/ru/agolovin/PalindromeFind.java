package ru.agolovin;

import java.util.Scanner;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class PalindromeFind {

    private Scanner scanner = new Scanner(System.in);

    private String word;

    public String ask(){
        System.out.println("Введите слово из пяти букв:");
        return scanner.nextLine();
    }

    public boolean isPalindrome(String str) {

        final byte wordLength = 5;

        boolean result = false;

        char[] subString = str.toLowerCase().toCharArray();

        if (subString.length != wordLength) {
            if (subString.length > wordLength) {
                System.out.println("В веденном слове больше 5 букв");
                result = false;
            } else {
                System.out.println("В веденном слове меньше 5 букв");
                result = false;
            }
        }

        for (int i = 0; i < wordLength / 2 ; i++) {
            if (subString[i] == subString[wordLength - i - 1]) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

}


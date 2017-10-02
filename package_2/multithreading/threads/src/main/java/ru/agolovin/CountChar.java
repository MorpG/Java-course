package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class CountChar implements Runnable {

    /**
     * String for parse.
     */
    private String str;

    /**
     * Constructor.
     *
     * @param str String.
     */
    CountChar(String str) {
        this.str = str;
    }

    /**
     * Count whiteSpaces.
     */
    private void countWhiteSpaces() {
        int count = 0;
        char[] arr = str.toCharArray();
        for (char element : arr) {
            if (element == ' ') {
                count++;
            }
        }
        System.out.println(String.format("Whitespace count: %s", count));
    }

    @Override
    public void run() {
        countWhiteSpaces();
        countChar();
    }

    /**
     * Count char.
     */
    private void countChar() {
        boolean flag = false;
        char[] arr = str.toCharArray();
        int count = 0;
        for (char element : arr) {
            if ((element != ' ') && !flag) {
                count++;
                flag = true;
            }
            if (element == ' ') {
                flag = false;
            }
        }
        System.out.println(String.format("Words count: %s", count));
    }
}


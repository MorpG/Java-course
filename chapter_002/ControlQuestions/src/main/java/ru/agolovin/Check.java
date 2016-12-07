package ru.agolovin;

/**
 * Check amount bracket class.
 */

public class Check {

    /**
     * validate amount bracket.
     * @param str String
     * @return boolean result
     */

    public boolean validate(final String str) {
        char[] subString = str.toCharArray();
        int count = 0;
        for (int i = 0; i < subString.length; i++) {
            if (subString[i] == '(') {
                count++;
            } else if (subString[i] == ')') {
                count--;
            }
        }

        return count == 0;
    }
}

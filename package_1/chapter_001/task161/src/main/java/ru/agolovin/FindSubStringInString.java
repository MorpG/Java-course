package ru.agolovin;


public class FindSubStringInString {
    public boolean contains(String orig, String sub) {
        boolean result = false;
        char[] origString = orig.toCharArray();
        char[] subString = sub.toCharArray();
        int j = 0;
        int origStringLength = origString.length;
        int subStringLength = subString.length;
        for (int i = 0; i < origStringLength; i++) {
            if (i + subStringLength > origStringLength)
                break;
            if (origString[i] == subString[j])
                j++;
            else
                j = 0;
            if (j == subStringLength - 1) {
                result = true;
                break;
            }
        }
        return result;
    }
}
package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class StringParse {

    /**
     * Incoming string.
     */
    private String str;

    /**
     * Constructor.
     *
     * @param str String.
     */
    StringParse(String str) {
        this.str = str;
    }

    /**
     * Main nethod.
     *
     * @param args String array
     */
    public static void main(String[] args) {
        String str = "word word2      word3";
        System.out.println(String.format("Incoming string: %s", str));
        StringParse stringParse = new StringParse(str);
        new Thread(stringParse.new CountWords()).start();
        new Thread(stringParse.new CountWhiteSpace()).start();


    }

    /**
     * Show whiteSpaces count in the String
     */
    public class CountWhiteSpace implements Runnable {
        @Override
        public void run() {
            int count = str.replaceAll("\\S+", "").toCharArray().length;
            System.out.println(String.format("Whitespace count: %s", count));
        }

    }

    /**
     * Show word count in the String.
     */
    public class CountWords implements Runnable {
        @Override
        public void run() {
            int count = str.split("\\s+").length;
            System.out.println(String.format("Words count: %s", count));
        }
    }

}

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
        System.out.println("Start");
        System.out.println(String.format("Incoming string: %s", str));
        StringParse stringParse = new StringParse(str);
        Thread threadOne = new Thread(stringParse.new CountWords());
        Thread threadTwo = new Thread(stringParse.new CountWhiteSpace());
        long startTime = System.currentTimeMillis();
        threadOne.start();
        try {
            threadOne.join(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadTwo.start();
        try {
            threadTwo.join(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (threadOne.isAlive() || threadTwo.isAlive()) {
            if ((threadOne.isAlive() || threadTwo.isAlive()) && (startTime - System.currentTimeMillis() < 1000)) {
                if (threadOne.isAlive()) {
                    stopThread(threadOne);
                }

                if (threadTwo.isAlive()) {
                    stopThread(threadTwo);
                }
            }
        }
        System.out.println("Finish");
    }

    /**
     * @param thread Thread
     */
    private static void stopThread(Thread thread) {
        thread.interrupt();
        if (thread.isInterrupted()) {
            try {
                throw new InterruptedException();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

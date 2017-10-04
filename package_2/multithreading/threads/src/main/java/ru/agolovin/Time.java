package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Time implements Runnable {

    /**
     * Thread.
     */
    private Thread threadIn;

    /**
     * Constructor.
     *
     * @param threadIn Thread
     */
    Time(Thread threadIn) {
        this.threadIn = threadIn;
    }

    /**
     * Main method.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        String str = "Incoming string";
        CountChar countChar = new CountChar(str);
        Thread threadChar = new Thread(countChar);
        Time time = new Time(threadChar);
        Thread timeThread = new Thread(time);
        timeThread.start();
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        threadIn.start();
        try {
            threadIn.join(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (threadIn.isAlive()) {
            if (threadIn.isAlive() && ((startTime - System.currentTimeMillis()) > 1000)) {
                threadIn.interrupt();
            }
        }
    }
}

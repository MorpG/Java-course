package ru.agolovin;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */


class Counter {

    /**
     * Expected result.
     */
    private static final int LIMIT = 100_000_000;

    /**
     * Count.
     */
    private static int anInt = 0;

    /**
     * Count hits.
     */
    private AtomicInteger lim = new AtomicInteger();

    /**
     * Main method.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        Counter problem = new Counter();
        Thread thread = new Thread(() -> {
            while (!problem.stop()) {
                anInt++;
            }
        });
        Thread thread2 = new Thread(() -> {
            while (!problem.stop()) {
                anInt++;
            }
        });
        thread.start();
        thread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("Expected %s", LIMIT));
        System.out.println(String.format("Result %s", anInt));
    }

    /**
     * Stop.
     *
     * @return boolean result
     */
    private boolean stop() {
        return lim.incrementAndGet() > LIMIT;
    }
}

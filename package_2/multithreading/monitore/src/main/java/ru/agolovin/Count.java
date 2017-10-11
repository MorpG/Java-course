package ru.agolovin;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

@ThreadSafe
class Count {

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
        Count count = new Count();
        Thread thread = new Thread(() -> {
            while (!count.stop()) {
                count.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            while (!count.stop()) {
                count.increment();
            }

        });
        thread.start();
        thread2.start();
        try {
            thread.join();
            thread2.join();
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

    @GuardedBy("this")
    private void increment() {
        synchronized (this) {
            anInt++;
        }
    }
}

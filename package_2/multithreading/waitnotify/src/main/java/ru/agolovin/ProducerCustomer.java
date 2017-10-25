package ru.agolovin;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class ProducerCustomer {

    /**
     * Queue.
     */
    private final Queue<Integer> queue = new LinkedBlockingQueue<>();

    /**
     * Element amount.
     */
    private int amount;

    /**
     * Marker.
     */
    private boolean flag = false;

    /**
     * Constructor.
     *
     * @param amount int
     */
    public ProducerCustomer(int amount) {
        this.amount = amount;
    }

    /**
     * Main method.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        new ProducerCustomer(20).init();
    }

    /**
     * ProducerCustomer start.
     */
    private void init() {
        Thread threadOne = this.producer();
        Thread threadTwo = this.customer();
        threadOne.start();
        threadTwo.start();
    }

    /**
     * Thread producer.
     *
     * @return Thread.
     */
    private Thread producer() {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (i < amount) {
                synchronized (queue) {
                    queue.add(i);
                    queue.notify();
                    System.out.println(String.format(
                            "Added %s by producer", i));
                    i++;
                }
                if (i == (amount - 1)) {
                    flag = true;
                }
                try {
                    Thread.sleep(35);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }

    /**
     * Thread customer.
     *
     * @return Thread
     */
    private Thread customer() {
        Thread thread = new Thread(() -> {
            synchronized (queue) {
                while (!flag) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(String.format("Removed %s by customer", queue.remove()));
                }
            }
        });
        return thread;
    }

}

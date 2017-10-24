package ru.agolovin;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.sleep;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class ProducerCustomer {

    private int quant = 5;

    private boolean flag = false;

    private Queue<Integer> queue = new LinkedBlockingQueue<>();

    public void init() {

    }

    private Thread producer() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < quant; i++) {
                synchronized (queue) {
                    queue.add(i++);
                    queue.notify();
                    System.out.println(String.format(
                            "Thread %s added %s", Thread.currentThread().getId(), i));
                }
                if ( i == (quant - 1)) {
                    flag = true;
                }
                try {
                    Thread.sleep(10);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }


}

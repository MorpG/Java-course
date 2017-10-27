package ru.agolovin;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class ThreadPool {

    /**
     * Lock object for threads.
     */
    private final Object lock = new Object();

    /**
     * Queue for threads work.
     */
    private final Queue<Work> queue = new LinkedBlockingQueue<>();

    /**
     * Monitor.
     */
    private boolean flag = false;

    /**
     * main method.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        threadPool.init(30);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.stopWork();
    }

    /**
     * Init all threads.
     *
     * @param amount int
     */
    private void init(int amount) {
        int coreNumber = Runtime.getRuntime().availableProcessors();
        System.out.println(String.format("Available processors %s", coreNumber));
        Thread[] threads = new Thread[coreNumber];
        for (int i = 0; i < coreNumber; i++) {
            threads[i] = this.createThread();
            threads[i].start();
        }
        for (int i = 0; i < amount; i++) {
            this.addWork(new Work(i));
        }
    }

    /**
     * Create thread.
     *
     * @return thread
     */
    private Thread createThread() {
        return new Thread(() -> {
            Work work;
            while (!this.flag) {
                synchronized (this.lock) {
                    while (this.queue.isEmpty() && !this.flag) {
                        try {
                            this.lock.wait(300);
                            System.out.println(String.format("Thread %s don`t work", Thread.currentThread().getId()));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (this.lock) {
                    work = this.queue.poll();
                }
                if (work != null) {
                    work.run();
                }
            }
        });
    }

    /**
     * Add Work to Queue.
     *
     * @param work Work
     */
    private void addWork(Work work) {
        this.queue.add(work);
        synchronized (this.lock) {
            this.lock.notifyAll();
        }
    }

    /**
     * Stop program evolution.
     */
    private void stopWork() {
        synchronized (this.lock) {
            this.lock.notifyAll();
            this.flag = true;

        }
    }

    /**
     * Some task.
     */
    private class Work implements Runnable {

        /**
         * Part number.
         */
        private int number;

        /**
         * Constructor.
         *
         * @param number int
         */
        Work(int number) {
            this.number = number;
        }

        /**
         * Information about current work.
         */
        @Override
        public void run() {
            System.out.println(String.format("Thread %s counting Task %s", Thread.currentThread().getId(), this.number));
        }
    }
}

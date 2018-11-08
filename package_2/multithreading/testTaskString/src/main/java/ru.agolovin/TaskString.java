package ru.agolovin;

import java.util.concurrent.Semaphore;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $
 * @since 0.1
 */
public class TaskString {

    private final int LIMIT = 10;
    private StringBuffer buffer = new StringBuffer();
    private Semaphore semaphore = new Semaphore(1);
    private int repeat = 1;

    public void setRepeatString(int number) {
        this.repeat = number;
    }


    public String getString() {
        return this.buffer.toString();
    }

    public void start() {
        Thread threadOne = new Thread(new StringThread(2));
        Thread threadTwo = new Thread(new StringThread(3));
        threadOne.start();
        threadTwo.start();
        try {
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class StringThread implements Runnable {
        private int anInt;

        public StringThread(int number) {
            this.anInt = number;
        }

        @Override
        public void run() {

            int repeatAmount = LIMIT * repeat;
            try {
                semaphore.acquire();
                for (int i = 1; i <= repeatAmount; i++) {
                    buffer.append(anInt);
                    if ((i % LIMIT) == 0) {
                        semaphore.release();
                        Thread.yield();
                        semaphore.acquire();
                    }
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

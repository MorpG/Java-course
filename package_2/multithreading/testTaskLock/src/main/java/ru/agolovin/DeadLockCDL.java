package ru.agolovin;


import java.util.concurrent.CountDownLatch;

public class DeadLockCDL {

    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();

        CountDownLatch cdl = new CountDownLatch(2);

        System.out.println("All object:");
        System.out.println(object1.toString());
        System.out.println(object2.toString());

        System.out.println("Start thread:");
        new Thread(new Lock(object1, object2, cdl)).start();
        new Thread(new Lock(object2, object1, cdl)).start();
    }
}

class Lock implements Runnable {
    final Object object1;
    final Object object2;
    CountDownLatch cdl;

    Lock(Object object1, Object object2, CountDownLatch cdl) {
        this.object1 = object1;
        this.object2 = object2;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        synchronized (object1) {
            System.out.println(object1.toString() + "is locked by " + Thread.currentThread().getName());
            cdl.countDown();
            try {
                cdl.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (object2) {
                System.out.println("Thread finished");
            }
        }
    }
}
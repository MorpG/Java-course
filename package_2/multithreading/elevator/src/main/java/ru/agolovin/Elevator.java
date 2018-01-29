package ru.agolovin;

//Симулятор лифта

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Elevator implements Runnable {

    private final int height;
    private final int speed;
    private final int stopTime;
    private int maxFloor;
    private int currentFloor;
    private boolean allowed;
    private PriorityBlockingQueue<Integer> calls;

    public Elevator(int maxFloor, int height, int speed, int stopTime, PriorityBlockingQueue<Integer> calls) {
        this.maxFloor = maxFloor;
        this.height = height;
        this.speed = speed;
        this.stopTime = stopTime;
        this.calls = calls;
    }

    private void init() {
        this.currentFloor = 1;
        do {
            try {
                move(calls.take());
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (allowed);
    }


    @Override
    public void run() {
        init();
    }

    private void move(int targetFloor) {
        try {
            if (targetFloor == 0) {
                this.allowed = false;
            }
            if (targetFloor == currentFloor) {
                turnDoor();
            } else {
                if (targetFloor > currentFloor) {
                    moveUP(targetFloor);
                    turnDoor();
                    this.currentFloor = targetFloor;
                } else {
                    moveDown(targetFloor);
                    turnDoor();
                    this.currentFloor = targetFloor;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveDown(int targetFloor) throws InterruptedException {
        for (int i = currentFloor; i >= targetFloor; i--) {
            messageCurrentFloor(i);
            TimeUnit.MICROSECONDS.sleep(speedDelay());
        }
    }

    private void moveUP(int targetFloor) throws InterruptedException {
        for (int i = currentFloor; i <= targetFloor; i++) {
            messageCurrentFloor(i);
            TimeUnit.MICROSECONDS.sleep(speedDelay());
        }

    }

    private int speedDelay() {
        return height / speed;
    }

    private void turnDoor() {
        System.out.println("Лифт открывает двери");
        try {
            TimeUnit.SECONDS.sleep(stopTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Лифт закрывает двери");
    }

    private void messageCurrentFloor(int i) {
        System.out.println(String.format("Лифт проехал %d этаж", i));
    }
}

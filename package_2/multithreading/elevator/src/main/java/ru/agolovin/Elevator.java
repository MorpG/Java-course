package ru.agolovin;

//Симулятор лифта

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

public class Elevator implements Runnable {

    private final int height;
    private final int speed;
    private final int stopTime;
    private int maxFloor;
    private int currentFloor;
    private boolean allowed;
    private ConcurrentSkipListSet<Integer> calls;

    public Elevator(int maxFloor, int height, int speed, int stopTime) {
        this.maxFloor = maxFloor;
        this.height = height;
        this.speed = speed;
        this.stopTime = stopTime;
        calls = new ConcurrentSkipListSet<>();
    }

    private void init() {
        this.currentFloor = 1;

    }

    private int speedDelay() {
        return height / speed;
    }

    @Override
    public void run() {
        do {
            move(targetFloor);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (allowed);
    }

    private void move(int targetFloor) {
        try {
            targetFloor--;
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
        for (int i = currentFloor; i > targetFloor; i--) {
            messageCurrentFloor(i);
            TimeUnit.SECONDS.sleep(speedDelay());
        }
    }

    private void moveUP(int targetFloor) throws InterruptedException {
        for (int i = currentFloor; i < targetFloor; i++) {
            TimeUnit.SECONDS.sleep(speedDelay());
            messageCurrentFloor(i);
        }

    }

    private void turnDoor() {
        try {
            System.out.println("Лифт открывает двери");
            TimeUnit.SECONDS.sleep(stopTime);
            System.out.println("Лифт закрывает двери");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void messageCurrentFloor(int i) {
        System.out.println(String.format("Лифт проехал %d этаж", i));
    }
}

package ru.agolovin;

//Симулятор лифта

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Elevator implements Runnable {

    /**
     * Высота этажа.
     */
    private final int height;

    /**
     * Скорость лифта.
     */
    private final int speed;

    /**
     * Время между открытием и закрытием дверей
     */
    private final int stopTime;

    /**
     * Текущий этаж.
     */
    private int currentFloor;

    /**
     * Текущий этаж.
     */
    private boolean allowed;

    /**
     * Очередь заявок.
     */
    private PriorityBlockingQueue<Integer> calls;

    /**
     * Конструктор.
     *
     * @param height   int
     * @param speed    int
     * @param stopTime int
     * @param calls    PriorityBlockingQueue
     */
    Elevator(int height, int speed, int stopTime, PriorityBlockingQueue<Integer> calls) {
        this.height = height;
        this.speed = speed;
        this.stopTime = stopTime;
        this.calls = calls;
        this.allowed = true;
    }

    /**
     * Инициализация метода.
     */
    private void init() {
        this.currentFloor = 1;
        do {
            try {
                if (!calls.isEmpty()) {
                    move(calls.take());
                    Thread.sleep(200);
                } else {
                    TimeUnit.SECONDS.sleep(1);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (allowed);
    }


    @Override
    public void run() {
        init();
    }

    /**
     * Перемещение лифта между этажами.
     *
     * @param targetFloor int
     */
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

    /**
     * Смещение лифта вниз.
     *
     * @param targetFloor int
     * @throws InterruptedException Exception
     */
    private void moveDown(int targetFloor) throws InterruptedException {
        for (int i = currentFloor; i >= targetFloor; i--) {
            messageCurrentFloor(i);
            TimeUnit.MICROSECONDS.sleep(speedDelay());
        }
    }

    /**
     * Смещение лифта вверх.
     *
     * @param targetFloor int
     * @throws InterruptedException Exception
     */
    private void moveUP(int targetFloor) throws InterruptedException {
        for (int i = currentFloor; i <= targetFloor; i++) {
            messageCurrentFloor(i);
            TimeUnit.MICROSECONDS.sleep(speedDelay());
        }

    }

    /**
     * Временная пауза.
     *
     * @return int
     */
    private int speedDelay() {
        return height / speed;
    }

    /**
     * Открытие/закрытие дверей.
     */
    private void turnDoor() {
        System.out.println("Лифт открывает двери");
        try {
            TimeUnit.SECONDS.sleep(stopTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Лифт закрывает двери");
    }

    /**
     * Отображение текущего этажа.
     *
     * @param i int
     */
    private void messageCurrentFloor(int i) {
        System.out.println(String.format("Лифт проехал %d этаж", i));
    }
}

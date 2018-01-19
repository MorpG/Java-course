package ru.agolovin;

//Симулятор лифта

public class Elevator implements Runnable{

    private int maxFloor;
    private int height;
    private int speed;
    private int stopTime;

    private int currentFloor;

    public Elevator(int maxFloor, int height, int speed, int stopTime) {
        this.maxFloor = maxFloor;
        this.height = height;
        this.speed = speed;
        this.stopTime = stopTime;
    }


    @Override
    public void run() {

    }
}

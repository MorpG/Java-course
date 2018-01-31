package ru.agolovin;

import java.util.concurrent.PriorityBlockingQueue;

public class ElevatorMain {

    private Elevator elevator;

    private UserInput input;

    private ElevatorMain(Elevator elevator, UserInput input) {
        this.elevator = elevator;
        this.input = input;
    }

    public static void main(String[] args) {
        try {
            if (args.length < 4) {
                throw new Exception("Введены не все параметры");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        int maxFloor = Integer.parseInt(args[0]);
        int heightLevel = Integer.parseInt(args[1]);
        int speed = Integer.parseInt(args[2]);
        int waitingTime = Integer.parseInt(args[3]);
        PriorityBlockingQueue<Integer> calls = new PriorityBlockingQueue<>();

        Elevator elevator = new Elevator(heightLevel, speed, waitingTime, calls);
        UserInput input = new UserInput(maxFloor, calls);
        ElevatorMain elevatorMain = new ElevatorMain(elevator, input);
        elevatorMain.startMain();
    }

    private void startMain() {
        new Thread(this.elevator).start();
        new Thread(this.input).start();
    }

}

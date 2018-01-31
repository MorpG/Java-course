package ru.agolovin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.PriorityBlockingQueue;

public class UserInput implements Runnable {

    private int maxFloor;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private PriorityBlockingQueue<Integer> calls;

    UserInput(int maxFloor, PriorityBlockingQueue<Integer> calls) {
        this.maxFloor = maxFloor;
        this.calls = calls;
    }

    @Override
    public void run() {
        boolean flag = true;
        do {
            System.out.println("1 - Вызов из кабины");
            System.out.println("2 - Вызов с этажа");
            System.out.println("0 - Остановка программы");
            int a = ask("Введите этаж");
            calls.put(a);
            if (a == 0) {
                flag = false;
            }
        } while (flag);
    }

    private int ask(String s) {
        boolean flag = false;
        int level = -1;
        System.out.println(s);
        do {
            try {
                String line = reader.readLine();
                try {
                    level = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода");
                    continue;
                }
                if (level >= 0 && level <= maxFloor) {
                    flag = true;
                } else {
                    System.out.println("Несуществующий этаж");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!flag);
        return level;
    }


}

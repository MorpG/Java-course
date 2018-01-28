package ru.agolovin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentSkipListSet;

public class UserInput implements Runnable {

    private int maxFloor;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private ConcurrentSkipListSet<Integer> callsInside = new ConcurrentSkipListSet<>();
    private ConcurrentSkipListSet<Integer> callsOutside = new ConcurrentSkipListSet<>();

    @Override
    public void run() {
        ask("S");
    }

    public int ask(String s) {
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
                if (level > 0 && level <= maxFloor) {
                    flag = true;
                } else {
                    System.out.println("Несущестующий этаж");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (flag);
        return level;
    }


}

package ru.agolovin;

import java.util.List;
import java.util.Random;

public class Warrior extends Figure {

    public Warrior(String name, Cell[][] board, Cell current) {
        super(name, board, current);
    }

    @Override
    String id() {
        return "Warrior";
    }

    @Override
    String getName() {
        return this.name;
    }

    @Override
    public void run() {
        List<Cell> allowed;
        Random rnd;

        while (!Bomberman.isStop()) {

            allowed = move(current);

            if (!allowed.isEmpty()) {

                while (true) {
                    rnd = new Random();
                    int select = rnd.nextInt(allowed.size());
                    if (allowed.get(select) != null) {
                        Cell dest = allowed.get(select);
                        synchronized (dest) {
                            if (!dest.getIsStop() && dest.getFigure() == null) {
                                tryStep(dest);
                                break;
                            } else if (dest.getFigure() != null
                                    && dest.getFigure().id().equals(
                                    "Bomberman")) {
                                System.out.println("Game over");
                                Bomberman.setStop(true);
                                break;

                            }
                            if (dest.getFigure() != null
                                    && dest.getFigure().id().equals("Warrior")) {
                                try {
                                    Thread.sleep(5000);
                                    System.out.println(
                                            String.format("Warrior %s make stop",
                                                    this.getName()));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }

                        }
                    }
                }
            }
        }
    }
}

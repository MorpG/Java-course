package ru.agolovin;

import java.util.List;
import java.util.Random;

public class Warrior extends Figure {

    /**
     * Constructor.
     *
     * @param name    String
     * @param board   Cell[][]
     * @param current Cell
     */
    Warrior(String name, Cell[][] board, Cell current) {
        super(name, board, current);
    }

    @Override
    String type() {
        return "Warrior";
    }

    @Override
    String getName() {
        return this.name;
    }

    @Override
    public void run() {
        System.out.println(String.format("Warrior %s start", this.name));
        List<Cell> allowed;
        Random rnd;
        while (!Bomberman.isStop()) {
            allowed = move(current);
            while (!Bomberman.isStop()) {
                rnd = new Random();
                int select = rnd.nextInt(allowed.size());
                if (allowed.get(select) != null) {
                    Cell dest = allowed.get(select);
                    synchronized (dest) {
                        if (dest.getIsStop() && dest.getFigure() == null) {
                            tryMakeStep(dest);
                            break;
                        } else if (dest.getFigure() != null
                                && dest.getFigure().type().equals("Bomberman")) {
                            System.out.println(String.format("Warrior %s damage player", this.getName()));
                            Bomberman.setStop(true);
                            break;
                        }
                        if (dest.getFigure() != null
                                && dest.getFigure().type().equals("Warrior") && !Bomberman.isStop()) {
                            try {
                                Thread.sleep(5000);
                                System.out.println(
                                        String.format("Warrior %s make stop because cell is busy",
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

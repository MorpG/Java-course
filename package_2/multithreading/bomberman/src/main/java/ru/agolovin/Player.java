package ru.agolovin;

import java.util.List;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Player extends Figure {

    Player(String name, Cell[][] board, Cell cell) {
        super(name, board, cell);
    }


    @Override
    String id() {
        return "Bomberman";
    }

    String getName() {
        return this.name;
    }

    @Override
    public void run() {
        System.out.println("Bomberman start moving");
        List<Cell> allowed;

        while (!Bomberman.isStop()) {
            allowed = move(current);
            for (Cell element : allowed) {
                if (element != null) {
                    synchronized (element) {
                        if (!element.getIsStop() && element.getFigure() == null) {
                            tryMakeStep(element);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (Bomberman.isStop()) {
                                Thread.interrupted();
                                break;
                            }
                        }
                    }
                }
                if (Bomberman.isStop()) {
                    break;
                }
            }
        }
    }
}

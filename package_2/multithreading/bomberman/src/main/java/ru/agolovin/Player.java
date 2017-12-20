package ru.agolovin;

import java.util.List;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Player extends Figure {

    public Player(String name, Cell[][] board, Cell cell) {
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


    }


}

package ru.agolovin.models;


public abstract class Figure {

    final Cell position;

    Figure(Cell sPosition) {
        this.position = sPosition;
    }

    public abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

    public abstract Figure clone(Cell dist);
}

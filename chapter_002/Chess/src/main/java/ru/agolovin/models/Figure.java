package ru.agolovin.models;

/**
 * Abstract class for figure logic.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
abstract class Figure {

    /**
     * position on Board.
     */
    private final Cell position;

    /**
     * constructor.
     * @param sPosition Cell
     */
    Figure(final Cell sPosition) {
        this.position = sPosition;
    }

    /**
     * array of cells figure way.
     * @param dist Cell
     * @return array of cells
     * @throws ImpossibleMoveException error
     */
    public abstract Cell[] way(final Cell dist) throws ImpossibleMoveException;

    /**
     * clone figure to new cell.
     * @param dist cell
     * @return new figure
     */
    public abstract Figure clone(final Cell dist);

    /**
     * Return Cell position.
     * @return position Cell
     */
    final Cell getPosition() {
        return position;
    }
}

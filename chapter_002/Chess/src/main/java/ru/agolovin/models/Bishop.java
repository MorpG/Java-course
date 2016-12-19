package ru.agolovin.models;

/**
 * Class for chess figure Bishop.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Bishop extends Figure {

    /**
     * constructor.
     * @param cell cell
     */
    public Bishop(final Cell cell) {
        super(cell);
    }

    /**
     * clone figure to dist cell.
     * @param dist cell
     * @return new figure.
     */
    @Override
    public final Bishop clone(final Cell dist) {
        return new Bishop(dist);
    }

    /**
     * array of move cells.
     * @param dist Cell
     * @return array of cells
     * @throws ImpossibleMoveException error
     */
    @Override
    public final Cell[] way(final Cell dist) throws ImpossibleMoveException {
        int startCol = this.getPosition().getCol();
        int startRow = this.getPosition().getRow();
        int endCol = dist.getCol();
        int endRow = dist.getRow();
        int maxSize = Math.abs(endCol - startCol);

        int step = 1;

        Cell[] res = new Cell[maxSize];

        if ((Math.abs(endCol - startCol) == Math.abs(endRow - startRow))
                && ((endCol - startCol) != 0) && (endRow - startRow) != 0) {
            if (startCol > endCol) {
                step = -1;
            }

            for (int i = 1; i <= maxSize; i++) {
                int stepCol = startCol + step * i;
                int stepRow = (stepCol - startCol) * (endRow - startRow)
                        / (endCol - startCol) + startRow;
                res[i - 1] = new Cell(startCol, stepRow);
            }
        } else {
            throw new ImpossibleMoveException("Error: impossible move");
        }

        return res;
    }
}

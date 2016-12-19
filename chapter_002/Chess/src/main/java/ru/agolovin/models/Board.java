package ru.agolovin.models;

/**
 * Class for chess board.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
class Board {

    /**
     * max size row and column on the board.
     */
    private final int maxSize = 8;

    /**
     * array of figure on the board.
     */
    private Figure[][] board = new Figure[maxSize][maxSize];

    /**
     * check correct move figure.
     *
     * @param start cell
     * @param end   cell
     * @return true if correct move
     * @throws ImpossibleMoveException error
     * @throws FigureNotFoundException error
     * @throws OccupiedWayException    error
     */
    final boolean move(final Cell start, final Cell end)
            throws ImpossibleMoveException,
            FigureNotFoundException, OccupiedWayException {
        boolean result = false;
        final int maxIndex = 7;

        try {
            int startCol = start.getCol();
            int startRow = start.getRow();
            if (startCol < 0 || startRow < 0
                    || startCol > maxIndex
                    || startRow > maxIndex
                    || (board[startCol][startRow] == null)) {
                throw new FigureNotFoundException(
                        "Error: Figure not found on the board");
            }

            if (end.getCol() < 0
                    || end.getCol() > maxIndex
                    || end.getRow() < 0
                    || end.getRow() > maxIndex) {
                throw new ImpossibleMoveException(
                        "Error: Destination out of the board");
            }

            Cell[] way = board[startCol][startRow].way(end);

            for (Cell element : way) {
                if (element == null) {
                    break;
                }

                if (board[element.getCol()][element.getRow()] != null) {
                    throw new OccupiedWayException(
                            "Another figure on the way");
                }
            }

            board[end.getCol()][end.getCol()] =
                    board[startCol][startRow].clone(end);

            board[startCol][startRow] = null;

            result = true;

        } catch (FigureNotFoundException fnfe) {
            System.out.println(fnfe);
        } catch (ImpossibleMoveException ime) {
            System.out.println(ime);
        } catch (OccupiedWayException owe) {
            System.out.println(owe);
        }
        return result;
    }
}

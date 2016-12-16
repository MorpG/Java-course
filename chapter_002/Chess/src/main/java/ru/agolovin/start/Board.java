package ru.agolovin.start;

import ru.agolovin.models.*;


public class Board {

    private final int maxSize = 8;

    public Figure[][] board = new Figure[maxSize][maxSize];

    boolean move(Cell start, Cell end) throws ImpossibleMoveException, FigureNotFoundException,
            OccupiedWayException {
        boolean result = false;

        try {
            int startCol = start.getCol();
            int startRow = start.getRow();

            if ((board[startCol][startRow] == null)
                || startCol < 0
                || startRow < 0
                || startCol > 7
                || startRow > 7) {
                throw new FigureNotFoundException("Error: Figure not found on the board");
            }

            if (end.getCol() < 0
                    || end.getCol() > 7
                    || end.getRow() < 0
                    || end.getRow() > 7) {
                throw new ImpossibleMoveException("Error: Destination out of the board");
            }

            Cell[] way = board[startCol][startRow].way(end);

            for (Cell element : way) {
                if (element == null) {
                    break;
                }

                if (board[element.getCol()][element.getRow()] != null) {
                    throw new OccupiedWayException("Another figure on the way");
                }
            }

            board[end.getCol()][end.getCol()] = board[startCol][startRow].clone(end);

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

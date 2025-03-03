package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

public final class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        figures[index++] = figure;
    }

    public void move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        int figureindex = findBy(source);
        Cell[] steps = figures[figureindex].way(dest);
        free(steps);
        figures[figureindex] = figures[figureindex].copy(dest);
    }

    private boolean free(Cell[] steps) throws OccupiedCellException {
        for (Cell step : steps) {
            for (Figure figure : figures) {
                if (figure != null && figure.position().equals(step)) {
                    throw new OccupiedCellException("Cell " + step + " is occupied");
                }
            }
        }
        return true;
    }

    public void clean() {
        Arrays.fill(figures, null);
        index = 0;
    }

    int findBy(Cell cell) throws FigureNotFoundException {
        for (int index = 0; index != figures.length; index++) {
            Figure figure = figures[index];
            if (figure != null && figure.position().equals(cell)) {
                return index;
            }
        }
        throw new FigureNotFoundException("Figure not found on the board.");
    }
}

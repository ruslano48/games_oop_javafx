package ru.job4j.chess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.white.BishopWhite;
import ru.job4j.chess.firuges.white.PawnWhite;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    void whenMoveSuccessfullyThenFigureMoves() throws Exception {
        Logic logic = new Logic();
        Figure pawn = new PawnWhite(Cell.D2);
        logic.add(pawn);

        logic.move(Cell.D2, Cell.D3);

        assertThat(logic.findBy(Cell.D3)).isNotNull();
    }

    @Test
    void whenMoveFigureNotFoundThenThrowException() {
        Logic logic = new Logic();

        assertThatThrownBy(() -> logic.move(Cell.D2, Cell.D3))
                .isInstanceOf(FigureNotFoundException.class)
                .hasMessage("Figure not found on the board.");
    }

    @Test
    void whenMoveImpossibleThenThrowException() {
        Logic logic = new Logic();
        Figure bishop = new BishopWhite(Cell.C1);
        logic.add(bishop);

        assertThatThrownBy(() -> logic.move(Cell.C1, Cell.C3))
                .isInstanceOf(ImpossibleMoveException.class);
    }

    @Test
    void whenMoveOccupiedCellThenThrowException() {
        Logic logic = new Logic();
        Figure bishop = new BishopWhite(Cell.C1);
        Figure pawn = new PawnWhite(Cell.D2);
        logic.add(bishop);
        logic.add(pawn);

        assertThatThrownBy(() -> logic.move(Cell.C1, Cell.E3))
                .isInstanceOf(OccupiedCellException.class);
    }
}

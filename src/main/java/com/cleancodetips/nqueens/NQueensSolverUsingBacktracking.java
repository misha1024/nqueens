package com.cleancodetips.nqueens;

import com.cleancodetips.nqueens.domain.Board;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.cleancodetips.nqueens.utilities.MathUtils.GCF;

/**
 * Solver for the following problem:
 * Place N queens on an NxN chess board so that none of them attack each other (the classic n-queens problem).
 * Additionally, please make sure that no three queens are in a straight line at ANY angle, so queens on A1, C2 and E3,
 * despite not attacking each other, form a straight line at some angle.
 */
@Component
public class NQueensSolverUsingBacktracking implements NQueensSolver {

    @Override
    public Collection<Board> solve(int numberOfQueens) {
        if (numberOfQueens < 1) {
            throw new IllegalArgumentException("Number of queens should be >= 1");
        }

        List<Board> boards = new ArrayList<>();
        solveRecursively(numberOfQueens, 0, new ArrayList<>(), boards);
        return boards;
    }

    private void solveRecursively(int numberOfQueens,
                                  int row,
                                  @NotNull List<Integer> columnPlacements,
                                  @NotNull List<Board> boards) {
        if (row == numberOfQueens) {
            boards.add(
                    createBoardFrom(columnPlacements, numberOfQueens));
            return;
        }

        for (int column = 0; column < numberOfQueens; column++) {
            columnPlacements.add(column);
            if (isValidMove(columnPlacements, numberOfQueens)) {
                solveRecursively(numberOfQueens, row + 1, columnPlacements, boards);
            }
            columnPlacements.remove(columnPlacements.size() - 1);
        }
    }

    private boolean isValidMove(@NotNull List<Integer> columnPlacements, int numberOfQueens) {
        return isNotAttacking(columnPlacements) && isNonCollinear(numberOfQueens, columnPlacements);
    }

    private boolean isNotAttacking(@NotNull List<Integer> columnPlacements) {
        int currentRow = columnPlacements.size() - 1;

        for (int i = 0; i < currentRow; i++) {
            int columnDistance = Math.abs(columnPlacements.get(i) - columnPlacements.get(currentRow));
            int rowDistance = currentRow - i;
            if (columnDistance == 0 || columnDistance == rowDistance) {
                return false;
            }
        }

        return true;
    }

    private boolean isNonCollinear(int numberOfQueens, List<Integer> columnPlacements) {

        int currentRow = columnPlacements.size() - 1;
        int currentColumn = columnPlacements.get(currentRow);

        for (int row = currentRow - 1; row > 0; row--) {
            int currentQueenRow = currentRow;
            int currentQueenColumn = currentColumn;
            int previousQueenRow = row;
            int previousQueenColumn = columnPlacements.get(row);

            int rise = currentQueenRow - previousQueenRow;
            int run = currentQueenColumn - previousQueenColumn;
            int gcf = Math.abs(GCF(rise, run));
            rise /= gcf;
            run /= gcf;

            for (int multiple = 1; previousQueenRow - multiple * rise >= 0; multiple++) {
                int extrapolatedRow = previousQueenRow - multiple * rise;
                int extrapolatedColumn = previousQueenColumn - multiple * run;
                if (extrapolatedRow >= 0 && extrapolatedRow < numberOfQueens
                        && extrapolatedColumn >= 0 && extrapolatedColumn < numberOfQueens
                        && columnPlacements.get(extrapolatedRow) == extrapolatedColumn) {
                    return false;
                }
            }
        }

        return true;
    }

    private Board createBoardFrom(@NotNull List<Integer> columnPlacements, int numberOfQueens) {
        Board board = new Board(numberOfQueens);
        int totalItemsPlaced = columnPlacements.size();

        for (int row = 0; row < totalItemsPlaced; row++) {
            for (int column = 0; column < numberOfQueens; column++) {
                if (column == columnPlacements.get(row)) {
                    board.setPosition(row, column, true);
                } else {
                    board.setPosition(row, column, false);
                }
            }
        }

        return board;
    }

}

package com.cleancodetips.nqueens;

import com.cleancodetips.nqueens.domain.Board;

import java.util.Collection;

public interface NQueensSolver {

    Collection<Board> solve(int numberOfQueens);
}

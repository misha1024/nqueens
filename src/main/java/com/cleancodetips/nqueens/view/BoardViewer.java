package com.cleancodetips.nqueens.view;

import com.cleancodetips.nqueens.domain.Board;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface BoardViewer {

    void view(@NotNull Board board);
    void viewCollection(@NotNull Collection<Board> boardsCollection);
}

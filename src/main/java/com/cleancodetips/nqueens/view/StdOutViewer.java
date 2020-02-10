package com.cleancodetips.nqueens.view;

import com.cleancodetips.nqueens.domain.Board;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;

@Component
public class StdOutViewer implements BoardViewer {

    @Override
    public void view(@NotNull Board board) {
        drawSeparator(board.getSize());
        drawBoard(board);
        drawSeparator(board.getSize());
    }

    @Override
    public void viewCollection(@NotNull Collection<Board> boardsCollection) {
        if (boardsCollection.isEmpty()) {
            System.out.println("No results found");
            return;
        }

        System.out.printf("Total # of boards found: %d\n\n", boardsCollection.size());
        boardsCollection.stream().forEach(this::view);
    }

    private void drawBoard(@NotNull Board board) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getPosition(i, j)) {
                    System.out.print(" Q ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }

    private void drawSeparator(int size) {
        IntStream.range(0, size*3).forEach((i) -> System.out.print("="));
        System.out.println();
    }

}

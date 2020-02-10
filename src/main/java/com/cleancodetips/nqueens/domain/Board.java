package com.cleancodetips.nqueens.domain;

public class Board {
    private boolean[][] positions;
    private int size;

    public Board(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Board size should be more that 0");
        }

        this.size = size;
        this.positions = new boolean[size][size];
    }

    public boolean getPosition(int i, int j) {
        checkRange(i, j);
        return positions[i][j];
    }

    public void setPosition(int i, int j, boolean value) {
        checkRange(i, j);
        positions[i][j] = value;
    }

    private void checkRange(int i, int j) {
        if ((i < 0 || i >= size) || (j < 0 || j >= size)) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getSize() {
        return size;
    }

}

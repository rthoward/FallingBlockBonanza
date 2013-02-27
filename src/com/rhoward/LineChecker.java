package com.rhoward;

public class LineChecker {

    private Grid grid;

    public LineChecker(Grid grid) {
        this.grid = grid;
    }

    public int checkLines() {
        int totalLinesCleared = 0;

        while ( (totalLinesCleared += clearBlankLines()) > 0) {
            this.grid.fillDown();
        }

        return totalLinesCleared;
    }

    private int clearBlankLines() {
        int linesCleared = 0;
        for (int line = 0; line < this.grid.getHeight(); line++) {
            if (isLineFull(line)) {
                this.grid.fillDown();
                ++linesCleared;
            }
        }
        return linesCleared;
    }

    private boolean isLineFull(int line) {
        for (int x = 0; x < this.grid.getWidth(); x++) {
            if (this.grid.getCell(x, line).isEmpty())
                return false;
        }
        return true;
    }


}

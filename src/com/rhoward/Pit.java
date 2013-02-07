package com.rhoward;

import java.util.ArrayList;
import java.util.List;

public class Pit {

    private final float X = 200;
    private final float Y = 30;

    private int WIDTH = 10;
    private int HEIGHT = 20;
    private float blockSize = Block.getBlockSize();
    private Grid grid;
    private Domino domino;

    EventListener eventListener;

    public Pit() {
        this.grid = new Grid(WIDTH, HEIGHT);
    }

    public void setEventListener(EventListener listener) {
        this.eventListener = listener;
    }

    public void add(Domino domino) {
        if (canFit(domino))
            this.domino = domino;
    }

    private boolean canFit(Domino domino) {

        List<Cell> currentDominoDisplaced = this.grid.calculateCellsDisplaced(this.domino);
        List<Cell> newDominoDisplaced = this.grid.calculateCellsDisplaced(domino);
        Cell currentCell;

        if (currentDominoDisplaced.isEmpty())
            return true;

        for (int x = 0; x < this.WIDTH; x++) {
            for (int y = 0; y < this.HEIGHT; y++) {

                currentCell = this.grid.getCell(x, y);

                // ignore cells displaced by current domino
                if (currentDominoDisplaced.contains(currentCell))
                    continue;

                if ( newDominoDisplaced.contains(currentCell) ) {
                    if (!currentCell.isEmpty())
                        return false;
                }
            }
        }

        return true;
    }




}

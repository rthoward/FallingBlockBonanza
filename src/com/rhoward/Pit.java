package com.rhoward;

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

        if (this.domino == null) {
            updateDomino(domino);
        }
        else if (canFit(domino))
            updateDomino(domino);
    }

    public void stepGravity() {
        if (!tryMoveDomino(0, 1)) {
            this.eventListener.onEvent(EventListener.EventType.DOMINO_FELL);
        }
    }

    public void draw() {

        this.grid.draw(this.X, this.Y);
    }

    public boolean tryMoveDomino(int x, int y) {
        Domino newState = this.domino.translate(x, y);
        if (canFit(newState)) {
            deleteDomino();
            updateDomino(newState);
            return true;
        }

        return false;
    }

    public boolean tryRotateDomino() {

        Domino newState = this.domino.rotate();

        if (canFit(newState)) {
            deleteDomino();
            updateDomino(newState);
            return true;
        }

        return false;
    }

    private boolean canFit(Domino domino) {

        if ( (domino.getX() < 0) || (domino.getY() < 0))
            return false;
        if ( (domino.getX() + domino.getWidth()) > WIDTH )
            return false;
        if ( (domino.getY() + domino.getHeight()) > HEIGHT)
            return false;

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

    private void updateDomino(Domino newState) {
        List<Cell> newDominoCells;
        newDominoCells = this.grid.calculateCellsDisplaced(newState);

        for (Cell cell : newDominoCells)
            cell.setBlockType(newState.getType());

        this.domino = newState;
    }

    private void deleteDomino() {
        List<Cell> oldDominoCells;
        oldDominoCells = this.grid.calculateCellsDisplaced(this.domino);

        for (Cell cell : oldDominoCells)
            cell.setBlockType(Block.BlockType.EMPTY);
    }




}

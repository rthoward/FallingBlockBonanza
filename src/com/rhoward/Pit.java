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

    public EventListener eventListener;
    private SoundManager soundManager;

    public Pit() {
        this.grid = new Grid(WIDTH, HEIGHT);
        this.soundManager = new SoundManager();
    }

    public void setEventListener(EventListener listener) {
        this.eventListener = listener;
    }

    public void add(Domino domino) {

        if (this.domino == null) {
            this.domino = domino;
        }
        else if (canFit(domino))
            this.domino = domino;
        else
            this.eventListener.onEvent(EventListener.EventType.PLAYER_LOST);
    }

    public void stepGravity() {
        if (!tryMoveDomino(0, 1)) {
            writeToGrid();
            this.eventListener.onEvent(EventListener.EventType.DOMINO_FELL);
        }
    }

    public void draw() {
        this.grid.draw(this.X, this.Y, this.domino);
    }

    public boolean tryMoveDomino(int x, int y) {
        Domino newState = this.domino.translate(x, y);
        if (canFit(newState)) {
            this.domino = newState;
            return true;
        }

        return false;
    }

    public boolean tryRotateDomino() {

        Domino newState = this.domino.rotate();

        if (canFit(newState)) {
            this.domino = newState;
            this.soundManager.playSound("rotate");
            return true;
        }

        return false;
    }

    private boolean canFit(Domino domino) {

        List<Coordinate> projectedCoordinates = domino.getCoordinatesDisplaced();

        for (Coordinate coord : projectedCoordinates) {
            if ( (coord.getX() < 0) || (coord.getX() >= this.WIDTH))
                return false;
            if ( (coord.getY() < 0) || (coord.getY() >= this.HEIGHT))
                return false;
            if ( !this.grid.getCell(coord.getX(), coord.getY()).isEmpty() )
                return false;
        }

        return true;
    }

    private void writeToGrid() {
        List<Cell> newDominoCells = this.grid.getCells(this.domino.getCoordinatesDisplaced());

        for (Cell cell : newDominoCells)
            cell.setBlockType(this.domino.getType());
    }

    public void checkLines() {
        for (int y = 0; y < this.HEIGHT; y++) {
            if (isLineFull(y)) {
                this.grid.clearLine(y);
                this.soundManager.playSound("clear_line");
                this.eventListener.onEvent(EventListener.EventType.LINE_CLEARED);
            }
        }
    }

    private boolean isLineFull(int line) {
        for (int x = 0; x < this.WIDTH; x++) {
            if (this.grid.getCell(x, line).isEmpty())
                return false;
        }

        return true;
    }
}

package com.rhoward;

import java.util.List;

public class Pit {

    private int positionX, positionY;
    private int width, height;

    private Grid grid;
    private ScoreEntity scoreBoard;
    private SoundManager soundManager;
    private Gravity gravity;
    private LineChecker lineChecker;
    private InputHandler inputHandler;

    private Domino currentDomino;
    private Domino nextDomino;

    private boolean paused = false;
    private boolean lost = false;

    public Pit(int positionX, int positionY, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;

        this.grid = new Grid(10, 20);
        this.inputHandler = new InputHandler(this);
        this.lineChecker = new LineChecker(this.grid);
        this.gravity = new Gravity(this);
        this.scoreBoard = new ScoreEntity(this.positionX + 200, this.positionY + 70);
        this.soundManager = new SoundManager();
    }

    public void init(Domino current, Domino next) {
        this.currentDomino = current;
        this.nextDomino = next;
    }

    public void draw() {
        this.grid.draw(this.positionX, this.positionY, this.currentDomino);
        this.scoreBoard.draw();
    }

    public void tryAddDomino(Domino domino) {

        if (this.nextDomino == null)
            this.nextDomino = domino;

        if (!canFit(this.currentDomino)) {
            System.out.println("YOU LOSE");
            this.lost = true;
        }
    }

    public void stepGravity() {
        if (!this.paused)
            this.gravity.stepGravity();
    }

    public void processInput(int delta) {
        this.inputHandler.processInput(delta);
    }

    public boolean tryMove(int x, int y) {
        Domino newState = this.currentDomino.translate(x, y);
        if (canFit(newState)) {
            this.currentDomino = newState;
            return true;
        }
        return false;
    }

    public boolean tryRotate() {
        Domino newState = this.currentDomino.rotate();

        if (canFit(newState)) {
            this.currentDomino = newState;
            this.soundManager.playSound("rotate");
            return true;
        }

        return false;
    }

    public void writeToGrid() {
        List<Cell> newDominoCells = this.grid.getCells(this.currentDomino.getCoordinatesDisplaced());

        for (Cell cell : newDominoCells)
            cell.setBlockType(this.currentDomino.getType());
    }

    public void onHitBottom() {
        this.scoreBoard.incrementScore(this.inputHandler.isHardDrop() ? 8 : 4);
        this.scoreBoard.incrementScore(this.lineChecker.checkLines() * 100);
        writeToGrid();
        this.currentDomino = nextDomino;
        this.nextDomino = null;
    }

    public void onPause() {
        this.paused = !this.paused;
        this.inputHandler.setAllowMovement(!paused);
        this.scoreBoard.setPaused(this.paused);
    }

    private boolean canFit(Domino domino) {
        List<Coordinate> projectedCoordinates = domino.getCoordinatesDisplaced();

        for (Coordinate coord : projectedCoordinates) {
            if ( (coord.getX() < 0) || (coord.getX() >= this.width))
                return false;
            if ( (coord.getY() < 0) || (coord.getY() >= this.height))
                return false;
            if ( !this.grid.getCell(coord.getX(), coord.getY()).isEmpty() )
                return false;
        }
        return true;
    }

    public boolean playerLost() {
        return this.lost;
    }
}
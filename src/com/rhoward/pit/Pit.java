package com.rhoward.pit;

import com.rhoward.StateManager;
import com.rhoward.domino.Domino;

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
    private ActionTimer tickTimer;
    private StateManager stateManager;

    private Domino currentDomino;
    private Domino nextDomino;

    private boolean lost = false;

    public Pit(int positionX, int positionY, int width, int height, StateManager stateManager) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;

        this.grid = new Grid(10, 20);
        this.tickTimer = new ActionTimer(300);
        this.stateManager = stateManager;
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
            this.lost = true;
            this.scoreBoard.setLost(this.lost);
            this.stateManager.onLose();
        }
    }

    public void logic(int delta) {

        this.tickTimer.update(delta);

        if (this.tickTimer.check())
            this.gravity.stepGravity();

    }

    public void processInput(int delta) {
        if (!this.lost)
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
        writeToGrid();
        this.scoreBoard.incrementScore(this.inputHandler.isHardDrop() ? 8 : 4);
        this.scoreBoard.incrementScore(this.lineChecker.checkLines() * 100);
        this.currentDomino = nextDomino;
        this.nextDomino = null;
    }

    public void onPause() {
        this.stateManager.onPause();
    }

    public void resetScore() {
        this.scoreBoard.setScore(0);
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

    public void clearGrid() {
        this.grid.clear();
        tryAddDomino(this.currentDomino);
    }
}
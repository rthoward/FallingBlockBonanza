package com.rhoward;

import java.util.ArrayList;
import java.util.List;

public class Pit {

    private final float X = 200;
    private final float Y = 30;

    private int WIDTH = 10;
    private int HEIGHT = 20;
    private float blockSize = Block.getBlockSize();
    private Block[][] pit = new Block[WIDTH][HEIGHT];
    private Domino domino;

    EventListener eventListener;

    public Pit() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                pit[x][y] = new Block(Block.BlockType.EMPTY);
            }
        }
    }

    public void setEventListener(EventListener listener) {
        this.eventListener = listener;
    }

    public void add(Domino domino) {
        if (canFit(domino)) {
            this.domino = domino;
            updatePit();
        }

    }

    public void draw() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                pit[x][y].draw( (x * blockSize) + X, (y * blockSize) + Y);
            }
        }

    }

    public void clear() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                pit[x][y].setType(Block.BlockType.EMPTY);
            }
        }
    }

    public void stepGravity() {

        clearDomino();

        Domino newState = this.domino.translate(0, 1);

        if ((newState.getHeight() < HEIGHT - 1) && canFit(newState)) {
            moveDomino(newState);
        }
        else {
            // TODO: domino has come to a resting state. implement hook?
            this.eventListener.onEvent(EventListener.EventType.DOMINO_FELL);
        }
        updatePit();
    }

    private void updatePit() {
        List<Coordinate> currentDominoCoordinates;

        currentDominoCoordinates = calculateCellsDisplaced(domino);

        for (Coordinate coordinate : currentDominoCoordinates) {
            this.pit[coordinate.getX()][coordinate.getY()].setType(domino.getType());
        }
    }

    private boolean canFit(Domino domino) {
        List<Coordinate> potentialCoordinates = calculateCellsDisplaced(domino);

        for (Coordinate coord : potentialCoordinates) {
            if (!isEmpty(coord.x, coord.y))
                return false;
        }

        return true;
    }

    private void moveDomino(Domino newState) {

       clearDomino();
       this.domino = newState;
    }

    private void clearDomino() {
        List<Coordinate> currentCoordinates = calculateCellsDisplaced(this.domino);

        for (Coordinate coord : currentCoordinates)
            pit[coord.getX()][coord.getY()].setType(Block.BlockType.EMPTY);
    }

    private List<Coordinate> calculateCellsDisplaced(Domino domino) {
        int dominoX = domino.getX();
        int dominoY = domino.getY();
        String dominoGrid = domino.getGrid();
        List<Coordinate> coordList = new ArrayList<Coordinate>(16);

        for (int i = 0; i < dominoGrid.length(); i++) {
            if (dominoGrid.charAt(i) == ',') {
                dominoY++;
                dominoX = domino.getX();
                continue;
            }

            if (dominoGrid.charAt(i) == '1')
                coordList.add(new Coordinate(dominoX, dominoY));

            dominoX++;
        }

        return coordList;
    }

    private boolean isEmpty(int x, int y) {
        return !((x >= WIDTH) || (y >= HEIGHT)) && pit[x][y].getType() == Block.BlockType.EMPTY;
    }

    private void sendEvent(EventListener.EventType eventType) {
        this.eventListener.onEvent(eventType);
    }

    private class Coordinate {

        private int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {return this.x;}
        public int getY() {return this.y;}
        public void setX(int x) {this.x = x;}
        public void setY(int y) {this.y = y;}
    }
}
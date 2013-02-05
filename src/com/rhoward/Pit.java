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
    private List<Domino> dominoList = new ArrayList<Domino>(16);

    public Pit() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                pit[x][y] = new Block(Block.BlockType.EMPTY);
            }
        }
    }

    public void add(Domino domino) {
        if (canFit(domino)) {
            this.dominoList.add(domino);
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
        clear();

    // TODO: need to move all blocks, not just dominos
//        for (int x = 0; x < WIDTH; x++) {
//            for (int y = 0; y < HEIGHT; y++) {
//                if (pit[x][y].getFallingState() == Block.FallingState.FALLING) {
//
//                }
//            }
//        }

        for (Domino domino : dominoList) {
            domino.setY(domino.getY() + 1);
        }

        updatePit();
    }

    private void updatePit() {
        List<Coordinate> currentDominoCoordinates;

        for (Domino domino : dominoList) {
            currentDominoCoordinates = calculateCellsDisplaced(domino);

            for (Coordinate coordinate : currentDominoCoordinates) {
                this.pit[coordinate.getX()][coordinate.getY()].setType(domino.getType());
            }
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
        return pit[x][y].getType() == Block.BlockType.EMPTY;
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
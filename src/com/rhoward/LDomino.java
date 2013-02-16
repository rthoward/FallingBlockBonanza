package com.rhoward;

import java.util.ArrayList;
import java.util.List;

public class LDomino extends Domino {

    public LDomino(Block.BlockType type, int x, int y) {
        super(type, x, y);
        this.normalShape = "010,010,011";
        this.leftShape = "001,111,000";
        this.downShape = "110,010,010";
        this.rightShape = "000,111,100";
    }

    @Override
    public Domino rotate() {
        LDomino newState = new LDomino(this.type, this.center.getX(), this.center.getY());
        newState.rotateState = this.rotateState;
        newState.rotateState = newState.nextRotation();

        return newState;
    }

    @Override
    public Domino translate(int x, int y) {
        LDomino translated = new LDomino(this.type, this.center.getX() + x, this.center.getY() + y);
        translated.rotateState = this.rotateState;
        return translated;
    }

    @Override
    public List<Coordinate> getCoordinatesDisplaced() {
        List<Coordinate> returnCoords = new ArrayList<Coordinate>(9);
        List<Coordinate> relativeCoords = calculateRelativeCoords();

        for (Coordinate coord : relativeCoords) {
            returnCoords.add(this.center.add(coord));
        }

        return returnCoords;
    }

    private List<Coordinate> calculateRelativeCoords() {
        String currentShape = getShape();
        List<Coordinate> returnCoords = new ArrayList<Coordinate>(9);
        int x = -1;
        int y = -1;

        for (int i = 0; i < currentShape.length(); i++) {
            if (currentShape.charAt(i) == ',') {
                y++;
                x = -1;
                continue;
            }

            if (currentShape.charAt(i) == '1') {
                returnCoords.add(new Coordinate(x, y));
            }

            x++;
        }

        return returnCoords;
    }

    private String getShape() {
        switch (this.rotateState) {
            case NORMAL:
                return normalShape;
            case LEFT:
                return leftShape;
            case DOWN:
                return downShape;
            case RIGHT:
                return rightShape;
            default:
                return "";
        }
    }

    @Override
    public int getHeight() {
        if ((this.rotateState == RotateState.NORMAL) || (this.rotateState == RotateState.DOWN))
            return 3;
        else
            return 2;
    }

    @Override
    public int getWidth() {
        if ((this.rotateState == RotateState.NORMAL) || (this.rotateState == RotateState.DOWN))
            return 2;
        else
            return 3;
    }
}

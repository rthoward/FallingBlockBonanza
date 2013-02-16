package com.rhoward;

import java.util.ArrayList;
import java.util.List;

import static com.rhoward.Block.BlockType;

public abstract class Domino {

    public enum RotateState {
        NORMAL, LEFT, DOWN, RIGHT;
    }

    protected RotateState rotateState;
    protected Coordinate center;

    protected String normalShape, leftShape, downShape, rightShape;

    BlockType type;

    public Domino(BlockType type, int x, int y) {
        this.type = type;
        this.rotateState = RotateState.NORMAL;
        this.center = new Coordinate(x, y);
    }

    public int getX() {
        return this.center.getX();
    }

    public int getY() {
        return this.center.getY();
    }

    public BlockType getType() {
        return type;
    }

    public List<Coordinate> getCoordinatesDisplaced() {
        List<Coordinate> returnCoords = new ArrayList<Coordinate>(9);
        List<Coordinate> relativeCoords = calculateRelativeCoords();

        for (Coordinate coord : relativeCoords) {
            returnCoords.add(this.center.add(coord));
        }

        return returnCoords;
    }


    protected String getShape() {
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

    protected List<Coordinate> calculateRelativeCoords() {
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

    public abstract Domino rotate();

    public abstract Domino translate(int x, int y);

    public void setX(int x) {
        this.center.setX(x);
    }

    public void setY(int y) {
        this.center.setY(y);
    }

    public abstract int getHeight();

    public abstract int getWidth();

    protected RotateState nextRotation() {
        return RotateState.values()[(this.rotateState.ordinal() + 1) % 4];
    }
}

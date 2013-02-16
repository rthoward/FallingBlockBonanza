package com.rhoward;

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

    public abstract Domino rotate();

    public abstract Domino translate(int x, int y);

    public abstract List<Coordinate> getCoordinatesDisplaced();

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

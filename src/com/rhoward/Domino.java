package com.rhoward;

import static com.rhoward.Block.BlockType;

public abstract class Domino {

    public enum RotateState {
        NORMAL, LEFT, RIGHT, DOWN;
    }

    protected RotateState rotateState;
    protected int x, y;

    BlockType type;

    public Domino(BlockType type, int x, int y) {
        this.type = type;
        this.rotateState = RotateState.NORMAL;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public BlockType getType() {
        return type;
    }

    public abstract String getGrid();

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

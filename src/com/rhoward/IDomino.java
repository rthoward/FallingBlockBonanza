package com.rhoward;

public class IDomino extends Domino {

    public IDomino(Block.BlockType type, int x, int y) {
        super(type, x, y);
        this.normalShape = "100,100,100,100";
        this.leftShape = "000,1111,000,000";
        this.downShape = "100,100,100,100";
        this.rightShape = "000,1111,000,000";
    }

    @Override
    public Domino rotate() {
        IDomino newState = new IDomino(this.type, this.getX(), this.getY());
        newState.rotateState = this.rotateState;
        newState.rotateState = newState.nextRotation();

        return newState;
    }

    @Override
    public Domino translate(int x, int y) {
        IDomino translated = new IDomino(this.type, this.getX() + x, this.getY() + y);
        translated.rotateState = this.rotateState;

        return translated;
    }

    @Override
    public int getHeight() {
        if ( (this.rotateState == RotateState.NORMAL) || (this.rotateState == RotateState.DOWN)) {
            return 4;
        }
        else
            return 1;
    }

    @Override
    public int getWidth() {
        if ( (this.rotateState == RotateState.NORMAL) || (this.rotateState == RotateState.DOWN)) {
            return 1;
        }
        else
            return 4;
    }
}

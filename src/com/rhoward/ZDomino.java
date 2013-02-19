package com.rhoward;

public class ZDomino extends Domino {

    public ZDomino(Block.BlockType type, int x, int y) {
        super(type, x, y);

        // TODO: rotations
        this.normalShape = "000,110,011";
        this.leftShape = "000,110,011";
        this.downShape = "000,110,011";
        this.rightShape = "000,110,011";
    }

    @Override
    public Domino translate(int x, int y) {
        ZDomino translated = new ZDomino(this.type, this.getX() + x, this.getY() + y);
        translated.rotateState = this.rotateState;
        return translated;
    }

    @Override
    public Domino rotate() {
        ZDomino newState = new ZDomino(this.type, this.getX(), this.getY());
        newState.rotateState = this.rotateState;
        newState.rotateState = newState.nextRotation();

        return newState;
    }
}

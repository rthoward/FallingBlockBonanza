package com.rhoward;

public class ZDomino extends Domino {

    public ZDomino(Block.BlockType type, int x, int y) {
        super(type, x, y);

        this.normalShape = "000,110,011";
        this.leftShape = "001,011,010";
        this.downShape = this.normalShape;
        this.rightShape = this.leftShape;
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

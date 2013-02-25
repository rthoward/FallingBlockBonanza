package com.rhoward;

import com.sun.xml.internal.ws.api.server.SDDocument;

public class SDomino extends Domino {

    public SDomino(Block.BlockType type, int x, int y) {
        super(type, x, y);

        this.normalShape = "000,011,110";
        this.leftShape = "010,011,001";
        this.downShape = this.normalShape;
        this.rightShape = this.leftShape;
    }

    @Override
    public Domino rotate() {
        SDomino newState = new SDomino(this.type, this.center.getX(), this.center.getY());
        newState.rotateState = this.nextRotation();

        return  newState;
    }

    @Override
    public Domino translate(int x, int y) {
        SDomino translated = new SDomino(this.type, this.getX() + x, this.getY() + y);
        translated.rotateState = this.rotateState;
        return translated;
    }
}

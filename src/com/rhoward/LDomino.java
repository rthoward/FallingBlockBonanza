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
        LDomino newState = new LDomino(this.type, this.getX(), this.getY());
        newState.rotateState = this.rotateState;
        newState.rotateState = newState.nextRotation();

        return newState;
    }

    @Override
    public Domino translate(int x, int y) {
        LDomino translated = new LDomino(this.type, this.getX() + x, this.getY() + y);
        translated.rotateState = this.rotateState;
        return translated;
    }
}

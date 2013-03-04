package com.rhoward.domino;

import com.rhoward.pit.Block;

public class JDomino extends Domino {

    public JDomino(Block.BlockType type, int x, int y) {
        super(type, x, y);
        this.normalShape = "010,010,110";
        this.leftShape = "000,111,001";
        this.downShape = "011,010,010";
        this.rightShape = "001,111,000";
    }

    @Override
    public Domino rotate() {
        JDomino newState = new JDomino(this.type, this.getX(), this.getY());
        newState.rotateState = this.rotateState;
        newState.rotateState = newState.nextRotation();

        return newState;
    }

    @Override
    public Domino translate(int x, int y) {
        JDomino translated = new JDomino(this.type, this.getX() + x, this.getY() + y);
        translated.rotateState = this.rotateState;
        return translated;
    }
}

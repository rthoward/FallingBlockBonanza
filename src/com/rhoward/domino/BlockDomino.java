package com.rhoward.domino;

import static com.rhoward.pit.Block.BlockType;

public class BlockDomino extends Domino {

    public BlockDomino(BlockType type, int x, int y) {
        super(type, x, y);

        this.normalShape = "000,011,011";
        this.leftShape = "000,011,011";
        this.downShape= "000,011,011";
        this.rightShape= "000,011,011";
    }

    @Override
    public Domino translate(int x, int y) {
        Domino translated = new BlockDomino(this.type, this.getX() + x, this.getY() + y);
        return translated;
    }

    @Override
    public Domino rotate() {
        return this;
    }
}
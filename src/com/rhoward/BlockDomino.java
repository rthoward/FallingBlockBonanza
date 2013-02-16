package com.rhoward;

import static com.rhoward.Block.BlockType;

public class BlockDomino extends Domino {

    public BlockDomino(BlockType type, int x, int y) {
        super(type, x, y);
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

    @Override
    public int getHeight() {
        return 2;
    }

    @Override
    public int getWidth() {
        return 2;
    }
}
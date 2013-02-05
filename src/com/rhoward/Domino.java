package com.rhoward;

import static com.rhoward.Block.BlockType;

public abstract class Domino {

    protected final float BLOCK_WIDTH = Block.getBLOCK_WIDTH();
    protected final float BLOCK_HEIGHT = Block.getBLOCK_HEIGHT();

    BlockType type;

    public Domino(BlockType type) {
        this.type = type;
    }

    public abstract int[] getGrid();
}

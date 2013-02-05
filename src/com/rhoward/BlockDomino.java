package com.rhoward;

import static com.rhoward.Block.BlockType;

public class BlockDomino extends Domino {

    public BlockDomino(BlockType type) {
        super(type);
    }

    @Override
    public int[] getGrid() {
        return new int[];
    }
}
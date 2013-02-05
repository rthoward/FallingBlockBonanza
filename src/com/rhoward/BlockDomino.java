package com.rhoward;

import static com.rhoward.Block.BlockType;

public class BlockDomino extends Domino {

    public BlockDomino(BlockType type, int x, int y) {
        super(type, x, y);
    }

    @Override
    public String getGrid() {
        String grid = new String();

        switch (rotateState) {
            case NORMAL:
                grid = "110,110";
                break;
            case DOWN:
                grid = "110,110";

                break;
            case LEFT:
                grid = "110,110";
                break;
            case RIGHT:
                grid = "110,110";
                break;
        }

        return grid;
    }
}
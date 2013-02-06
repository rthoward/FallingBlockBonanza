package com.rhoward;

import static com.rhoward.Block.BlockType;

public class BlockDomino extends Domino {

    public BlockDomino(BlockType type, int x, int y) {
        super(type, x, y);
    }

    @Override
    public Domino translate(int x, int y) {
        Domino translated = new BlockDomino(this.type, this.x + x, this.y + y);
        return translated;
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

    @Override
    public int getHeight() {
        return 2;
    }

    @Override
    public int getWidth() {
        return 2;
    }
}
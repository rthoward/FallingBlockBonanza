package com.rhoward;

public class ZDomino extends Domino {

    public ZDomino(Block.BlockType type, int x, int y) {
        super(type, x, y);
    }

    @Override
    public Domino translate(int x, int y) {
        ZDomino translated = new ZDomino(this.type, this.x + x, this.y + y);
        return translated;
    }

    @Override
    public String getGrid() {
        String grid;

        switch (rotateState) {
            case NORMAL:
                grid = "110,011";
                break;
            case DOWN:
                grid = "110,011";
                break;
            case LEFT:
                grid = "01,11,10";
                break;
            case RIGHT:
                grid = "01,11,10";
                break;
            default:
                grid = "";
                break;
        }

        return grid;
    }

    @Override
    public int getHeight() {
        if ( (this.rotateState == RotateState.NORMAL) || (this.rotateState == RotateState.DOWN) )
            return 2;
        else
            return 3;
    }

    @Override
    public int getWidth() {
        if ( (this.rotateState == RotateState.NORMAL) || (this.rotateState == RotateState.DOWN))
            return 3;
        else return 2;
    }
}

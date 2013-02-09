package com.rhoward;

public class TDomino extends Domino {


    public TDomino(Block.BlockType type, int x, int y) {
        super(type, x, y);
    }

    @Override
    public Domino rotate() {
        TDomino newState = new TDomino(this.type, this.x, this.y);
        newState.rotateState = this.rotateState;
        newState.rotateState = newState.nextRotation();

        return newState;
    }

    @Override
    public Domino translate(int x, int y) {
        TDomino translated = new TDomino(this.type, this.x + x, this.y + y);
        translated.rotateState = this.rotateState;
        return translated;
    }

    @Override
    public String getGrid() {
        String grid;

        switch (this.rotateState) {
            case NORMAL:
                grid = "111,010";
                break;
            case LEFT:
                grid = "10,11,10";
                break;
            case DOWN:
                grid = "010,111";
                break;
            case RIGHT:
                grid = "01,11,01";
                break;
            default:
                grid = "";
                break;
        }
        return grid;
    }

    @Override
    public int getHeight() {
        if ((this.rotateState == RotateState.NORMAL) || (this.rotateState == RotateState.DOWN))
            return 2;
        else
            return 3;
    }

    @Override
    public int getWidth() {
        if ((this.rotateState == RotateState.NORMAL) || (this.rotateState == RotateState.DOWN))
            return 3;
        else
            return 2;
    }
}

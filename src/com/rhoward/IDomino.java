package com.rhoward;

public class IDomino extends Domino {

    public IDomino(Block.BlockType type, int x, int y) {
        super(type, x, y);
    }

    @Override
    public Domino rotate() {
        IDomino newState = new IDomino(this.type, this.x, this.y);
        newState.rotateState = this.rotateState;
        newState.rotateState = newState.nextRotation();

        return newState;
    }

    @Override
    public Domino translate(int x, int y) {
        IDomino translated = new IDomino(this.type, this.x + x, this.y + y);
        translated.rotateState = this.rotateState;

        return translated;
    }

    @Override
    public String getGrid() {
        String grid;

        switch (rotateState) {
            case NORMAL:
                grid = "1,1,1,1";
                break;
            case RIGHT:
                grid = "1111";
                break;
            case DOWN:
                grid = "1,1,1,1";
                break;
            case LEFT:
                grid = "1111";
                break;
            default:
                grid = "";
                break;
        }

        return grid;
    }

    @Override
    public int getHeight() {
        if ( (this.rotateState == RotateState.NORMAL) || (this.rotateState == RotateState.DOWN)) {
            return 4;
        }
        else
            return 1;
    }

    @Override
    public int getWidth() {
        if ( (this.rotateState == RotateState.NORMAL) || (this.rotateState == RotateState.DOWN)) {
            return 1;
        }
        else
            return 4;
    }
}

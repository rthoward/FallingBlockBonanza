package com.rhoward;


public class Cell {

    private int x, y;
    private Block block;

    public Cell(int x, int y, Block block) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Cell other) {
        return ( this.x == other.getX() &&
                 this.y == other.getY() );
    }

    public boolean isEmpty() {
        return (this.getBlockType() == Block.BlockType.EMPTY);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Block getBlock() {
        return this.block;
    }

    public Block.BlockType getBlockType() {
        return this.block.getType();
    }

    public void setBlockType(Block.BlockType type) {
        this.block.setType(type);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}

package com.rhoward;

import java.util.Random;

public class DominoFactory {

    private final int NUM_PIECES = 6;
    private final int NUM_COLORS = 4;

    private int startx, starty;
    private Random rand;

    public DominoFactory(int startx, int starty) {
        this.rand = new Random();
        this.startx = startx;
        this.starty = starty;
    }

    public Domino newDomino() {
        Domino newDomino;
        int newTypeRand = rand.nextInt(NUM_PIECES) + 1;
        Block.BlockType newType;

        switch (newTypeRand) {
            case 1:
                newDomino = new BlockDomino(Block.BlockType.YELLOW, startx, starty);
                break;
            case 2:
                newDomino = new LDomino(Block.BlockType.ORANGE, startx, starty);
                break;
            case 3:
                newDomino = new ZDomino(Block.BlockType.RED, startx, starty);
                break;
            case 4:
                newDomino = new IDomino(Block.BlockType.CYAN, startx, starty);
                break;
            case 5:
                newDomino = new TDomino(Block.BlockType.PURPLE, startx, starty);
                break;
            case 6:
                newDomino = new SDomino(Block.BlockType.GREEN, startx, starty);
                break;
            default:
                newDomino = null;
                break;
        }

        return newDomino;
    }
}

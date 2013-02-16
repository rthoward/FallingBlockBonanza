package com.rhoward;

import java.util.Random;

public class DominoFactory {

    private final int NUM_PIECES = 5;
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
        int newColorRand = rand.nextInt(NUM_COLORS) + 1;
        Block.BlockType newType;

        switch (newColorRand) {
            case 1:
                newType = Block.BlockType.RED;
                break;
            case 2:
                newType = Block.BlockType.BLUE;
                break;
            case 3:
                newType = Block.BlockType.GREEN;
                break;
            case 4:
                newType = Block.BlockType.YELLOW;
                break;
            default:
                newType = Block.BlockType.EMPTY;
                break;
        }

        newTypeRand = 2;

//        switch (newTypeRand) {
//            case 1:
//                //newDomino = new BlockDomino(newType, startx, starty);
//                break;
//            case 2:
//                newDomino = new LDomino(newType, startx, starty);
//                break;
//            case 3:
//                //newDomino = new ZDomino(newType, startx, starty);
//                break;
//            case 4:
//                //newDomino = new IDomino(newType, startx, starty);
//                break;
//            case 5:
//                //newDomino = new TDomino(newType, startx, startx);
//                break;
//            default:
//                newDomino = null;
//                break;
//        }

        newDomino = new LDomino(newType, startx, starty);

        return newDomino;
    }
}

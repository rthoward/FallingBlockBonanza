package com.rhoward;

public class Pit {

    private int WIDTH = 10;
    private int HEIGHT = 20;
    private Block[][] pit = new Block[WIDTH][HEIGHT];

    public Pit() {
        clear();
    }

    public void add(Domino domino) {

    }

    public void clear() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                pit[x][y] = null;
            }
        }
    }



}
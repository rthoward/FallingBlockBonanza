package com.rhoward;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private final float blockSize = Block.getBlockSize();

    private Cell[][] grid;
    private int height, width;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];
    }

    public Cell getCell(int x, int y) {
        return this.grid[x][y];
    }

    public boolean isEmpty(int x, int y) {
        return (this.grid[x][y].isEmpty());
    }

    public List<Cell> calculateCellsDisplaced(Domino domino) {

        List<Cell> displacedList = new ArrayList<Cell>(8);

        if (domino == null)
            return displacedList;

        int dominoX = domino.getX();
        int dominoY = domino.getY();
        String dominoGrid = domino.getGrid();

        for (int i = 0; i < dominoGrid.length(); i++) {
            if (dominoGrid.charAt(i) == ',') {
                dominoY++;
                dominoX = domino.getX();
                continue;
            }

            if (dominoGrid.charAt(i) == '1')
                displacedList.add(this.getCell(dominoX, dominoY));

            dominoX++;
        }

        return displacedList;
    }

    public void draw(int posX, int posY) {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                grid[x][y].getBlock().draw( (x * blockSize) + posX, (y * blockSize) + posY);
            }
        }
    }

    private void initialize() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.grid[x][y] = new Cell(x, y, new Block(Block.BlockType.EMPTY));
            }
        }
    }
}

package com.rhoward.pit;

import com.rhoward.domino.Domino;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private final float blockSize = Block.getBlockSize();
    private Cell[][] grid;
    private int height;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private int width;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];
        initialize();
    }

    public Cell getCell(int x, int y) {
        return this.grid[x][y];
    }

    public List<Cell> getCells(List<Coordinate> coordinateList) {
        List<Cell> returnCells = new ArrayList<Cell>(9);

        for (Coordinate coord : coordinateList) {
            returnCells.add(getCell(coord.getX(), coord.getY()));
        }

        return returnCells;
    }

    public boolean isEmpty(int x, int y) {
        return (this.grid[x][y].isEmpty());
    }

    public void draw(float posX, float posY, Domino domino) {

        List<Coordinate> dominoCoords = domino.getCoordinatesDisplaced();

        // apply domino to grid
        for (Coordinate coord : dominoCoords) {
            grid[coord.getX()][coord.getY()].setBlockType(domino.getType());
        }

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                grid[x][y].getBlock().draw( (x * blockSize) + posX, (y * blockSize) + posY);
            }
        }

        // remove domino from grid
        for (Coordinate coord : dominoCoords) {
            grid[coord.getX()][coord.getY()].setBlockType(Block.BlockType.EMPTY);
        }
    }

    private void initialize() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.grid[x][y] = new Cell(x, y, new Block(Block.BlockType.EMPTY));
            }
        }
    }

    public void clear() {
        initialize();
    }

    public void clearLine(int line) {
        for (int x = 0; x < this.width; x++) {
            grid[x][line].setBlockType(Block.BlockType.EMPTY);
        }

        for (int x = 0; x < this.width; x++) {
            for (int y = line; y >= 0; y--) {
                if (y == 0)
                    this.grid[x][y].setBlockType(Block.BlockType.EMPTY);
                else
                    this.grid[x][y].setBlockType(this.grid[x][y - 1].getBlockType());
            }
        }
    }

    public void fillDown() {
        for (int x = 0; x < this.width; x++) {
            for (int y = this.height - 2; y > 0; y--) {
                if (this.grid[x][y + 1].getBlockType() == Block.BlockType.EMPTY) {
                    this.grid[x][y + 1].setBlockType(this.grid[x][y].getBlockType());
                }
            }
        }
    }
}

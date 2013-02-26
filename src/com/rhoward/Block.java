package com.rhoward;

import static org.lwjgl.opengl.GL11.*;

public class Block {

    private static final float BLOCK_SIZE = 16;
    private static final float BLOCK_BORDER = 2.0f;
    private final float COLOR_SIZE = BLOCK_SIZE - BLOCK_BORDER;
    private BlockType type;
    private FallingState fallingState;

    public Block(BlockType type) {
        this.type = type;
    }

    public static float getBlockSize() {
        return BLOCK_SIZE;
    }

    public static float getBlockBorder() {
        return BLOCK_BORDER;
    }

    public void draw(float x, float y) {
        float red, green, blue;
        red = green = blue = 0.0f;

        switch (this.type) {
            case EMPTY:
                // grey
                red = 100.0f; green = 100.0f; blue = 100.0f;
                break;
            case BLUE:
                red = 0.0f; green = 128.0f; blue = 255.0f;
                break;
            case GREEN:
                red = 0.0f; green = 255.0f; blue = 128.0f;
                break;
            case RED:
                red = 255.0f; green = 51.0f; blue = 51.0f;
                break;
            case YELLOW:
                red = 255.0f; green = 255.0f; blue = 102.0f;
                break;
            case CYAN:
                red = 0.0f; green = 255.0f; blue = 255.0f;
                break;
            case ORANGE:
                 red = 255.0f; green = 127.0f; blue = 0.0f;
                break;
            case PURPLE:
                red = 128.0f; green = 0.0f; blue = 128.0f;
                break;
        }

        glColor3f(red / 255.0f, green / 255.0f, blue / 255.0f);

        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + COLOR_SIZE, y);
        glVertex2f(x + COLOR_SIZE, y + COLOR_SIZE);
        glVertex2f(x, y + COLOR_SIZE);
        glEnd();
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public FallingState getFallingState() {
        return fallingState;
    }

    public void setFallingState(FallingState fallingState) {
        this.fallingState = fallingState;
    }

    public enum BlockType {
        EMPTY, RED, GREEN, BLUE, YELLOW, CYAN, ORANGE, PURPLE;
    }

    public enum FallingState {
        FALLING, GROUNDED;
    }
}

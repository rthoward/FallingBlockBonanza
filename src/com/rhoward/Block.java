package com.rhoward;

import static org.lwjgl.opengl.GL11.*;

public class Block {

    public enum BlockType {
        RED, GREEN, BLUE, YELLOW;
    }

    private float x, y;
    private BlockType type;

    public Block(BlockType type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    private final float BLOCK_HEIGHT = 32;
    private final float BLOCK_WIDTH = 32;

    public void draw() {
        switch (type) {
            case RED:
                glColor3f(1.0f, 0.0f, 0.0f);
            break;
            case GREEN:
                glColor3f(0.0f, 1.0f, 0.0f);
            break;
            case BLUE:
                glColor3f(0.0f, 0.0f, 1.0f);
            break;
            case YELLOW:
                glColor3f(0.0f, 0.5f, 0.5f);
            break;
        }
        glBegin(GL_QUADS);
            glVertex2f(x, y);
            glVertex2f(x + BLOCK_WIDTH, y);
            glVertex2f(x + BLOCK_WIDTH, y + BLOCK_HEIGHT);
            glVertex2f(x, y + BLOCK_HEIGHT);
        glEnd();
    }

}

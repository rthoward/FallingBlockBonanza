package com.rhoward;

import static org.lwjgl.opengl.GL11.*;

public class Block {

    public enum BlockType {
        RED, GREEN, BLUE, YELLOW;
    }

    private final float BLOCK_HEIGHT = 32;
    private final float BLOCK_WIDTH = 32;

    private float x, y;
    private BlockType type;
    private float termVelocityY = 10.0f;

    // temp
    private float velocityX;
    private float velocityY;

    public Block(BlockType type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public void update(int delta) {
        this.x += ((float)delta / 100) * this.velocityX;
        this.y += ((float)delta / 100) * this.velocityY;
    }

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

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public void modVelocityX(float velocityX) {
        this.velocityX += velocityX;
    }

    public void modVelocityY(float velocityY) {
        this.velocityY += velocityY;
    }
}

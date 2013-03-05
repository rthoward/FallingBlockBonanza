package com.rhoward.pit;

import org.newdawn.slick.opengl.Texture;

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

        String textureName = null;

        switch (this.type) {
            case EMPTY:
                // grey
                glColor3f(30.0f, 30.0f, 30.0f);
                glBegin(GL_QUADS);
                    glVertex2f(x, y);
                    glVertex2f(x + COLOR_SIZE, y);
                    glVertex2f(x + COLOR_SIZE, y + COLOR_SIZE);
                    glVertex2f(x, y + COLOR_SIZE);
                glEnd();
                return;
            case BLUE:
                textureName = "blue";
                break;
            case GREEN:
                textureName = "green";
                break;
            case RED:
                textureName = "red";
                break;
            case YELLOW:
                textureName = "yellow";
                break;
            case CYAN:
                textureName = "cyan";
                break;
            case ORANGE:
                textureName = "orange";
                break;
            case PURPLE:
                textureName = "purple";
                break;
        }

        Texture texture = TextureManager.getInstance().getTexture(textureName);

        System.out.println("printing " + textureName);

        texture.bind();
        glBegin(GL_QUADS);
            glTexCoord2f(0, 0);
            glVertex2f(x, y);
            glTexCoord2f(1, 0);
            glVertex2f(x + COLOR_SIZE, y);
            glTexCoord2f(1, 1);
            glVertex2f(x + COLOR_SIZE, y + COLOR_SIZE);
            glTexCoord2f(0, 1);
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

package com.rhoward;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.ArrayList;
import java.util.List;

public class FallingBlockBonanza {

    // window attributes
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private static final String TITLE = "FALLING BLOCK BONANZA";
    private int delta = 0;

    // timing
    private static final int FPS = 60;
    private long lastFrame;

    private boolean isRunning = true;

    // entities
    private List<Block> blocks;
    private List<BlockDomino> blockDominos = new ArrayList<BlockDomino>(16);
    private Gravity gravity;

    public FallingBlockBonanza() {
        initDisplay();
        initOpenGL();
        initWorld();
        initEntities();
        initTimer();

        while (isRunning) {
            delta = getDelta();
            logic(delta);
            render();
            input(delta);
            Display.update();
            Display.sync(FPS);

            if (Display.isCloseRequested()) { isRunning = false; }
        }

        Display.destroy();
    }

    private void logic(int delta) {
        this.gravity.stepGravity(delta);

        for (BlockDomino domino : blockDominos) {
            domino.update(delta);
        }
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        for (BlockDomino domino : blockDominos) {
            domino.draw();
        }
    }

    private void input(int delta) {

    }

    private void initDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle(TITLE);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    private void initTimer() {
        lastFrame = getTime();
    }

    private void initWorld() {
        gravity = new Gravity(blockDominos);
    }

    private void initEntities() {
        this.blockDominos.add(new BlockDomino(Block.BlockType.BLUE, 100, 100));
    }

    private void initOpenGL() {
        glLoadIdentity();
        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glPushMatrix();
    }

    private static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    private int getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }

    public static void main(String[] args) {
        FallingBlockBonanza fbb = new FallingBlockBonanza();
    }
}

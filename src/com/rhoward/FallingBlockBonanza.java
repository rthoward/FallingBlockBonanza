package com.rhoward;

import static org.lwjgl.opengl.GL11.*;

import com.rhoward.pit.Pit;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class FallingBlockBonanza {

    // window attributes
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private static final String TITLE = "FALLING BLOCK BONANZA";
    private int delta = 0;

    // for counting domino movement
    private int tickTime = 300;
    private int tickCounter = 0;

    // timing
    private static final int FPS = 60;
    private long lastFrame;

    private boolean isRunning = true;

    private Pit pit;
    private DominoFactory dominoFactory;

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

        AL.destroy();
        Display.destroy();
    }

    private void logic(int delta) {

        if (this.pit.playerLost())
            return;

        this.pit.logic(delta);

        this.pit.tryAddDomino(dominoFactory.newDomino());
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        pit.draw();
    }

    private void input(int delta) {
        this.pit.processInput(delta);
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
        this.pit = new Pit(200, 30, 10, 20);
    }

    private void initEntities() {
        this.dominoFactory = new DominoFactory(4, 1);
        this.pit.init(this.dominoFactory.newDomino(), this.dominoFactory.newDomino());
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

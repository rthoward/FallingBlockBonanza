package com.rhoward;

import static org.lwjgl.opengl.GL11.*;

import com.rhoward.menu.Menu;
import com.rhoward.pit.Pit;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class FallingBlockBonanza {

    // window attributes
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private static final String TITLE = "FALLING BLOCK BONANZA";
    private int delta = 0;

    private GameState state = GameState.PLAYING;

    // timing
    private static final int FPS = 60;
    private long lastFrame;

    private boolean isRunning = true;

    private Pit pit;
    private DominoFactory dominoFactory;
    private StateManager stateManager;
    private Menu pauseMenu;

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

        this.state = this.stateManager.getState();

        if (this.state == GameState.LOST)
            return;
        else if (this.state == GameState.PLAYING) {
            this.pit.logic(delta);
            this.pit.tryAddDomino(dominoFactory.newDomino());
        }
        else if (this.state == GameState.PAUSED)
            this.pauseMenu.logic();
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        if (this.state != GameState.PAUSED)
            pit.draw();
        else
            this.pauseMenu.draw();

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
        this.stateManager = new StateManager();
        this.pit = new Pit(200, 30, 10, 20, this.stateManager);
        this.pauseMenu = new Menu(this.pit, this.stateManager);
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

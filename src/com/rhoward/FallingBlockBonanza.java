package com.rhoward;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.ArrayList;
import java.util.List;

public class FallingBlockBonanza implements EventListener{

    // window attributes
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private static final String TITLE = "FALLING BLOCK BONANZA";
    private int delta = 0;

    // for counting domino movement
    private final int TICK_TIME = 300;
    private int tickCounter = 0;

    // timing
    private static final int FPS = 60;
    private long lastFrame;
    private boolean newPiece = false;

    private boolean isRunning = true;
    private boolean paused = false;
    private boolean lost = false;

    // entities
    private List<Block> blocks;
    private List<Domino> dominos = new ArrayList<Domino>(16);

    private Pit pit;
    private DominoFactory dominoFactory;
    private InputHandler inputHandler;
    private ScoreEntity scoreHandler;

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

        if (this.lost) {
            System.out.println("you lost!");
            return;
        }

        if (this.paused)
            return;

        tickCounter += delta;

        if (tickCounter >= TICK_TIME) {
            tickCounter = 0;
            pit.stepGravity();
        }

        if (newPiece)
            this.pit.add(this.dominoFactory.newDomino());

        newPiece = false;
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        pit.draw();
        scoreHandler.draw();

    }

    private void input(int delta) {
        this.inputHandler.processInput(delta);
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
        this.pit = new Pit();
        this.pit.setEventListener(this);
        this.inputHandler = new InputHandler(this.pit);
        this.scoreHandler = new ScoreEntity(400, 100);

        this.scoreHandler.setScore(0);
    }

    private void initEntities() {
        this.dominoFactory = new DominoFactory(4, 4);

        this.pit.add(this.dominoFactory.newDomino());
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

    @Override
    public void onEvent(EventType eventType) {
        switch (eventType) {
            case DOMINO_FELL:
                newPiece = true;
                this.pit.checkLines();
                this.scoreHandler.incrementScore(this.inputHandler.isHardDrop() ? 8 : 4);
                break;
            case PLAYER_LOST:
                this.paused = true;
                break;
            case LINE_CLEARED:
                this.scoreHandler.incrementScore(100);
                break;
            case PAUSE:
                this.paused = !this.paused;
                break;
        }
    }
}

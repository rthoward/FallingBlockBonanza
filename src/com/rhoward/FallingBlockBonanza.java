package com.rhoward;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
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

    // entities
    private List<Block> blocks;
    private List<Domino> dominos = new ArrayList<Domino>(16);

    private Pit pit;
    private DominoFactory dominoFactory;
    private InputHandler inputHandler;

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
    }

    private void initEntities() {
        //pit.add(new BlockDomino(Block.BlockType.BLUE, 0, 0));
        //pit.add(new LDomino(Block.BlockType.GREEN, 0, 0));
        //pit.add(new ZDomino(Block.BlockType.RED, 0, 0));
        this.dominoFactory = new DominoFactory(4, 0);

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
                break;
            case PLAYER_LOST:
                // end the game
                break;
        }
    }
}

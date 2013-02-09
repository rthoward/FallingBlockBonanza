package com.rhoward;

import org.lwjgl.input.Keyboard;

public class InputHandler {

    private final int MOVE_TIMEOUT = 50;
    private final int ROTATE_TIMEOUT = 150;
    private final int PAUSE_TIMEOUT = 100;

    private Pit pit;
    private ActionTimer moveTimer;
    private ActionTimer rotateTimer;
    private ActionTimer pauseTimer;


    public InputHandler(Pit pit) {
        this.pit = pit;
        initTimers();
    }

    public void processInput(int delta) {

        int moveX = 0;
        int moveY = 0;

        // translational movement keys

        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            moveX++;
        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            moveX--;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            moveY++;
        }

        // other keys

        while (Keyboard.next()) {
            switch (Keyboard.getEventKey()) {
                case Keyboard.KEY_W:
                    if (Keyboard.getEventKeyState() && this.rotateTimer.check())
                        this.pit.tryRotateDomino();
                    break;
                case Keyboard.KEY_ESCAPE:
                    if (Keyboard.getEventKeyState() && this.pauseTimer.check())
                        this.pit.eventListener.onEvent(EventListener.EventType.PAUSE);
                    break;
                default:

                    break;
            }
        }


        if ( (moveX != 0) || (moveY !=0) ) {
            if (this.moveTimer.check())
                this.pit.tryMoveDomino(moveX, moveY);
        }

        updateTimers(delta);
    }

    private void initTimers() {
        this.moveTimer = new ActionTimer(this.MOVE_TIMEOUT);
        this.rotateTimer = new ActionTimer(this.ROTATE_TIMEOUT);
        this.pauseTimer = new ActionTimer(this.PAUSE_TIMEOUT);
    }

    private void updateTimers(int delta) {
        this.moveTimer.update(delta);
        this.rotateTimer.update(delta);
        this.pauseTimer.update(delta);
    }
}

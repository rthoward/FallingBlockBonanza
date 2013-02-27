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

    private boolean hardDrop = false;
    private boolean allowMovement = true;
    private boolean allowPause = true;

    public InputHandler(Pit pit) {
        this.pit = pit;
        initTimers();
    }

    public void processInput(int delta) {

        int moveX = 0;
        int moveY = 0;

        // translational movement keys

        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            moveX++;
        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            moveX--;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            moveY++;
            this.hardDrop = true;
        }
        else
            this.hardDrop = false;

        // other keys

        while (Keyboard.next()) {
            switch (Keyboard.getEventKey()) {
                case Keyboard.KEY_UP:
                    if (Keyboard.getEventKeyState() && this.rotateTimer.check() && this.allowMovement)
                        this.pit.tryRotate();
                    break;
                case Keyboard.KEY_ESCAPE:
                    if (Keyboard.getEventKeyState() && this.pauseTimer.check() && this.allowPause)
                        this.pit.onPause();
                    break;
                default:

                    break;
            }
        }


        if ( ( (moveX != 0) || (moveY !=0)) && this.allowMovement ) {
            if (this.moveTimer.check())
                this.pit.tryMove(moveX, moveY);
        }

        updateTimers(delta);
    }

    public boolean isHardDrop() {
        return this.hardDrop;
    }

    public void setAllowPause(boolean allow) {
        this.allowPause = allow;
    }

    public void setAllowMovement(boolean allow) {
        this.allowMovement = allow;
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

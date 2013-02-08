package com.rhoward;

import org.lwjgl.input.Keyboard;

public class InputHandler {

    private final int MOVE_TIMEOUT = 100;
    private final int ROTATE_TIMEOUT = 100;

    private Pit pit;
    private ActionTimer moveTimer;
    private ActionTimer rotateTimer;


    public InputHandler(Pit pit) {
        this.pit = pit;
        initTimers();
    }

    public void processInput(int delta) {

        // TODO: input is laggy

        int moveX = 0;
        int moveY = 0;

        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            moveX++;
        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            moveX--;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            moveY++;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            if (this.rotateTimer.updateCheck(delta))
                this.pit.tryRotateDomino();
        }

        if ( (moveX != 0) || (moveY !=0) ) {
            if (this.moveTimer.updateCheck(delta))
                this.pit.tryMoveDomino(moveX, moveY);
        }
    }

    private void initTimers() {
        this.moveTimer = new ActionTimer(this.MOVE_TIMEOUT);
        this.rotateTimer = new ActionTimer(this.ROTATE_TIMEOUT);
    }
}

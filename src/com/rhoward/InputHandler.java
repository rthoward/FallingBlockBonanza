package com.rhoward;

import org.lwjgl.input.Keyboard;

public class InputHandler {

    private Pit pit;


    public InputHandler(Pit pit) {
        this.pit = pit;
    }

    public void processInput() {

        int moveX = 0;
        int moveY = 0;

        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            moveX++;
            System.out.println("hit d");
        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            moveX--;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            this.pit.tryRotateDomino();
        }

        if ( (moveX != 0) || (moveY !=0)) {
            this.pit.tryMoveDomino(moveX, moveY);
        }


    }
}

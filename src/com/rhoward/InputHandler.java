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

//        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
//            moveY--;
//        }
//        else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
//            moveY++;
//        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            // rotate
        }

        // if move was requested, try to move
        if ( (moveX != 0) || (moveY != 0)) {
            if (this.pit.canMove(moveX, moveY))
                this.pit.moveDomino(moveX, moveY);
            else {
                System.out.println("can't move");
            }
        }
        else {
            System.out.println("no move registered");
        }

    }
}

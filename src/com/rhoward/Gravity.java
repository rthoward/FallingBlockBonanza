package com.rhoward;

public class Gravity {

    private Pit pit;

    public Gravity(Pit pit) {
        this.pit = pit;
    }

    public void stepGravity() {
        // if block collides on bottom, notify Pit
        if (!this.pit.tryMove(0, 1)) {
            this.pit.onHitBottom();
        }
    }

}

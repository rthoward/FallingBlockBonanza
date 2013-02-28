package com.rhoward.pit;

public class ActionTimer {

    private long timeCounter;
    private int targetTime;

    public ActionTimer(int timeMillis) {
        this.targetTime = timeMillis;
        this.timeCounter = 0;
    }

    public void update(int delta) {
        this.timeCounter += delta;
    }

    public boolean check() {
        if (this.timeCounter >= this.targetTime) {
            this.timeCounter = 0;
            return true;
        }

        return false;
    }
}

package com.rhoward;

public class ActionTimer {

    private int timeCounter;
    private int targetTime;

    public ActionTimer(int timeMillis) {
        this.targetTime = timeMillis;
        this.timeCounter = 0;
    }

    public boolean updateCheck(int delta) {
        this.timeCounter += delta;

        if (this.timeCounter >= this.targetTime) {
            this.timeCounter = this.timeCounter - this.targetTime;
            return true;
        }

        return false;
    }
}

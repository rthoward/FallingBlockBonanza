package com.rhoward;


public class StateManager {

    private GameState currentState = GameState.PLAYING;

    public void onPause() {
        if (this.currentState == GameState.PLAYING)
            this.currentState = GameState.PAUSED;
        else if (this.currentState == GameState.PAUSED)
            this.currentState = GameState.PLAYING;
    }

    public void onLose() {
        this.currentState = GameState.LOST;
    }

    public GameState getState() {
        return this.currentState;
    }

}

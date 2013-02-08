package com.rhoward;

public interface EventListener {

    public enum EventType {
        DOMINO_FELL, PLAYER_LOST, LINE_CLEARED;
    }

    public void onEvent(EventType eventType);
}

package com.rhoward;

public interface EventListener {

    public enum EventType {
        DOMINO_FELL, PLAYER_LOST;
    }

    public void onEvent(EventType eventType);
}

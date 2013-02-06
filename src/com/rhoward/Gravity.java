package com.rhoward;

import java.util.ArrayList;
import java.util.List;

public class Gravity {

    private final float GRAV_SPEED = 10.0f;
    private List<Domino> dominoList = null;

    public Gravity(List<Domino> blockList) {
        this.dominoList = blockList;
    }

    public void stepGravity(int delta) {
        for (Domino domino : dominoList) {
        }
    }
}

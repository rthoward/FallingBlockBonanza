package com.rhoward;

import java.util.ArrayList;
import java.util.List;

public class Gravity {

    private final float GRAV_SPEED = 10.0f;
    private List<Block> blockList = null;

    public Gravity(List<Block> blockList) {
        this.blockList = blockList;
    }

    public void stepGravity(int delta) {
        for (Block block : blockList) {
            block.modVelocityY( ((float)delta / 100) * GRAV_SPEED );
        }
    }
}

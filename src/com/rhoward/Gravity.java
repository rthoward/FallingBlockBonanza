package com.rhoward;

import java.util.ArrayList;
import java.util.List;

public class Gravity {

    private final float GRAV_SPEED = 10.0f;
    private List<BlockDomino> blockList = null;

    public Gravity(List<BlockDomino> blockList) {
        this.blockList = blockList;
    }

    public void stepGravity(int delta) {
        for (BlockDomino block : blockList) {
            block.modVelocityY( ((float)delta / 100) * GRAV_SPEED );
        }
    }
}

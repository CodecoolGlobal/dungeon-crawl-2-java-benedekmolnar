package com.codecool.dungeoncrawl.logic.actors.movable.monster;


import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Direction;

import java.util.Arrays;

public class Ghost extends Monster {
    private int motionInd = 0;

    public Ghost(GameMap map, Cell cell, Direction direction) {
        super(map, cell, direction, 5);
        killable = false;
        coolDownTimer = 10;
    }

    @Override
    public String getTileName() {
        return "ghost" + motionInd % 2;
    }

    @Override
    public void act() {
        if (coolDown == 0) motionInd++;
        action();
    }

    @Override
    protected void changeDir() {
        if (!Arrays.asList(passable).contains(cell.getNeighborByDir(direction).getType())) {
            direction = direction.getReversDirection();
        }
    }
}

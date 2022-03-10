package com.codecool.dungeoncrawl.logic.actors.movable.monster;


import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Direction;

import java.util.Arrays;

public class Ghost extends Monster {
    public Ghost(GameMap map, Cell cell, Direction direction) {
        super(map, cell, direction, 5);
        killable = false;
        coolDownTimer = 10;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    @Override
    public void act() {
        action();
    }

    @Override
    protected void changeDir() {
        if (!Arrays.asList(passable).contains(cell.getNeighborByDir(direction).getType())) {
            direction = direction.getReversDirection();
        }
    }
}

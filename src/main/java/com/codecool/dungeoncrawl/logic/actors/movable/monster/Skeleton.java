package com.codecool.dungeoncrawl.logic.actors.movable.monster;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.Killable;
import com.codecool.dungeoncrawl.logic.actors.movable.Movable;

public class Skeleton extends Monster implements Killable {
    public Skeleton(GameMap map, Cell cell) {
        super(map, cell, Direction.UP, 2);
        health = 25;
        killable = true;
        coolDownTimer = 15;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    public void act() {
        if (isDead(this)) {
            kill(map, this);
            return;
        }
        action();
    }

    @Override
    protected void changeDir() {
        direction = Direction.getRandDirection();
    }
}

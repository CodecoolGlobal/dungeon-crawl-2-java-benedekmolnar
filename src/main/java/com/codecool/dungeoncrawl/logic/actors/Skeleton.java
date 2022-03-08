package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Skeleton extends Movable implements Killable {
    public Skeleton(GameMap map, Cell cell) {
        super(map, cell, Direction.UP);
        killable = true;
        coolDownTimer = 5;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    public void act() {
        if (isDead(this)) kill(map, this);
        if (coolDown == 0) {
            move(0, 1);
            coolDown = coolDownTimer;
        }
        else coolDown--;
    }
}

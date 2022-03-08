package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Movable {
    public Skeleton(Cell cell) {
        super(cell, Direction.UP);
        coolDownTimer = 5;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    public void act() {
        if (coolDown == 0) {
            move(0, 1);
            coolDown = coolDownTimer;
        }
        else coolDown--;
    }
}

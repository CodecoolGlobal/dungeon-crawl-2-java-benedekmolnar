package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Projectile extends Movable{
    public Projectile(Cell cell, Direction direction) {
        super(cell, direction);
        super.coolDownTimer = 1;
    }

    public void act() {
        moveToDir();
    }

    @Override
    public String getTileName() {
        return "projectile";
    }

}

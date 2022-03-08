package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Projectile extends Movable implements Killable{
    public Projectile(GameMap map, Cell cell, Direction direction) {
        super(map, cell, direction);
        super.coolDownTimer = 1;
    }

    public void act() {
        if(cell.getNeighborByDir(direction).getActor() != null ||
           cell.getNeighborByDir(direction).getType() == CellType.WALL )
            wound(111, this);
        if (isDead(this)) kill(map, this);
        moveToDir();
    }

    @Override
    public String getTileName() {
        return "projectile";
    }
}

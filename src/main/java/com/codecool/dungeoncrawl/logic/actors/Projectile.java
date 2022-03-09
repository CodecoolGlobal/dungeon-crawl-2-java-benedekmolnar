package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.Arrays;

public class Projectile extends Movable implements Killable{
    private CellType[] walls = new CellType[]{CellType.LEFTWALL, CellType.RIGHTWALL, CellType.UPWALL, CellType.DOWNWALL};

    public Projectile(GameMap map, Cell cell, Direction direction) {
        super(map, cell, direction);
        killable = true;
    }

    public void act() {
        Cell nextCell = cell.getNeighborByDir(direction);
        Actor nextActor = nextCell.getActor();
        if (nextActor != null) {
            if (nextActor.isKillable())
                nextActor.setHealth(nextActor.getHealth() - 5);
            kill(map, this);
        }
        if (Arrays.asList(walls).contains(nextCell.getType()))
            kill(map, this);
        moveToDir();
    }

    @Override
    public String getTileName() {
        return "projectile";
    }
}

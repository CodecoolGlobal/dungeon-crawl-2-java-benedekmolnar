package com.codecool.dungeoncrawl.logic.actors.movable.projectile;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.Killable;
import com.codecool.dungeoncrawl.logic.actors.movable.Movable;

import java.util.Arrays;

public class Projectile extends Movable implements Killable{

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
        if (!Arrays.asList(passable).contains(nextCell.getType()))
            kill(map, this);
        moveToDir();
    }

    @Override
    public String getTileName() {
        return "projectile";
    }
}

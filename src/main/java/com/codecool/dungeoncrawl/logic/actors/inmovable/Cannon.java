package com.codecool.dungeoncrawl.logic.actors.inmovable;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.movable.projectile.Projectile;

public class Cannon extends Actor {
    public Cannon(GameMap map, Cell cell) {
        super(cell, map);
        coolDownTimer = 15;
    }

    @Override
    public String getTileName() {
        return "cannon";
    }

    @Override
    public void act() {
        if (coolDown == 0) {
            shoot();
            coolDown = coolDownTimer;
        } else coolDown--;
    }

    private void shoot() {
        shootToDir(Direction.LEFT);
        shootToDir(Direction.RIGHT);
        shootToDir(Direction.UP);
        shootToDir(Direction.DOWN);
    }

    private void shootToDir(Direction dir) {
        if (cell.getNeighborByDir(dir).getType() == CellType.FLOOR &&
            cell.getNeighborByDir(dir).getActor() == null)
            map.addToActors(new Projectile(map, cell.getNeighborByDir(dir), dir));
    }
}

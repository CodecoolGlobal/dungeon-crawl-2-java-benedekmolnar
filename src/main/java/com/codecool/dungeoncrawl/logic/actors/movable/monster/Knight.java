package com.codecool.dungeoncrawl.logic.actors.movable.monster;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.Killable;

public class Knight extends Monster implements Killable {
    private Cell startCell;

    public Knight(GameMap map, Cell cell) {
        super(map, cell, Direction.NONE, 3);
        startCell = cell;

        health = 50;
        killable = true;
        coolDownTimer = 5;
    }

    @Override
    public String getTileName() {
        return "knight";
    }

    @Override
    public void act() {
        if (isDead(this)){
            kill(map, this);
            return;
        }
        action();
    }

    @Override
    protected void changeDir() {
        if (cell.isActorNear(6, map.getPlayer())) {
            direction = cell.dirTowardCell(map.getPlayer().getCell());
            return;
        }
        if (startCell != cell) {
            direction = cell.dirTowardCell(startCell);
        } else {
            direction = Direction.NONE;
        }
    }
}

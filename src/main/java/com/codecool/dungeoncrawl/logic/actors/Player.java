package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Player extends Movable {
    private char lastOrder;
    protected GameMap map;

    public Player(Cell cell, GameMap map) {
        super(cell, Direction.UP);
        this.map = map;
        lastOrder = ' ';
        super.coolDownTimer = 3;
    }

    public String getTileName() {
        return "player";
    }

    public void act() {
        if (super.coolDown == 0) {
            if (lastOrder == 'w') changeDirection(Direction.UP);
            if (lastOrder == 'd') changeDirection(Direction.RIGHT);
            if (lastOrder == 's') changeDirection(Direction.DOWN);
            if (lastOrder == 'a') changeDirection(Direction.LEFT);
            if (lastOrder == 'y') shoot();

            moveToDir();

            lastOrder = ' ';
            coolDown = coolDownTimer;
        }
        else coolDown--;
    }

    private void shoot() {
        if (cell.getNeighborByDir(direction).getType() == CellType.FLOOR)
            map.addToActors(new Projectile(cell.getNeighborByDir(direction), direction));
    }

    public void setLastOrder(char order) {
        lastOrder = order;
    }
}

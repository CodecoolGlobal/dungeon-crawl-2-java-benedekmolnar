package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Player extends Movable {
    private char lastOrder;

    public Player(GameMap map, Cell cell) {
        super(map, cell, Direction.UP);
        killable = true;
        lastOrder = ' ';
        super.coolDownTimer = 3;
    }

    public String getTileName() {
        return "player";
    }

    public void act() {
        if (super.coolDown == 0) {
            switch (lastOrder) {
                case 'w':
                    changeDirection(Direction.UP);
                    moveToDir();
                    break;
                case 'd':
                    changeDirection(Direction.RIGHT);
                    moveToDir();
                    break;
                case 's':
                    changeDirection(Direction.DOWN);
                    moveToDir();
                    break;
                case 'a':
                    changeDirection(Direction.LEFT);
                    moveToDir();
                    break;
                case 'y':
                    shoot();
                    break;
            }
            lastOrder = ' ';
            coolDown = coolDownTimer;
        }
        else coolDown--;
    }

    private void shoot() {
        if (cell.getNeighborByDir(direction).getType() != CellType.WALL &&
            cell.getNeighborByDir(direction).getActor() == null)
            map.addToActors(new Projectile(map, cell.getNeighborByDir(direction), direction));
    }

    public void setLastOrder(char order) {
        lastOrder = order;
    }
}

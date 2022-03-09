package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public abstract class Movable extends Actor {
    protected Direction direction;
    private CellType[] passable = new CellType[] {CellType.FLOOR};

    public Movable(GameMap map, Cell cell, Direction direction) {
        super(cell, map);
        this.direction = direction;
    }

    public void moveToDir() {
        if (direction == Direction.LEFT) move(-1, 0);
        if (direction == Direction.RIGHT) move(1, 0);
        if (direction == Direction.UP) move(0, -1);
        if (direction == Direction.DOWN) move(0, 1);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getActor() == null && nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.NEXTLEVEL) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction dir) {
        direction = dir;
    }

    public void changeDirection(Direction dir) {
        direction = dir;
    }
}

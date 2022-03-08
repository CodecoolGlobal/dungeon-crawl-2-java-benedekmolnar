package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public abstract class Movable extends Actor {
    protected Direction direction;

    public Movable(Cell cell, Direction direction) {
        super(cell);
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
        if (nextCell.getActor() == null && nextCell.getType() != CellType.WALL) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void changeDirection(Direction dir) {
        direction = dir;
    }
}

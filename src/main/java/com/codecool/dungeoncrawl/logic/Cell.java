package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.actors.Direction;

import java.util.Dictionary;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Item item;
    private GameMap gameMap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public boolean isActorNear(int distance, Actor actor) {
        if (Math.abs(actor.getCell().x - x) < distance &&
            Math.abs(actor.getCell().y - y) < distance) {
            return true;
        }
        return false;
    }

    public Direction dirTowardCell(Cell cell) {
        if (cell.x < x) return Direction.LEFT;
        if (cell.x > x) return Direction.RIGHT;
        if (cell.y < y) return Direction.UP;
        if (cell.y > y) return Direction.DOWN;
        return Direction.NONE;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    public Cell getNeighborByDir(Direction direction) {
        if (direction == Direction.UP) return getNeighbor(0, -1);
        if (direction == Direction.DOWN) return getNeighbor(0, 1);
        if (direction == Direction.LEFT) return getNeighbor(-1, 0);
        if (direction == Direction.RIGHT) return getNeighbor(1, 0);
        return getNeighbor(0, 0);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

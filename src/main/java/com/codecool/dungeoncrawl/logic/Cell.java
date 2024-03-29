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

    public String typeToString() {
        switch (type) {
            case WALL:
                return "#";
            case FLOOR:
                return ".";
            case GRASS:
                return ",";
            case TREE:
                return "?";
            case BUSH:
                return ";";
            case EMPTY:
                return " ";
            case UPROOF:
                return "U";
            case DOWNROOF:
                return "D";
            case LEFTROOF:
                return "L";
            case RIGHTROOF:
                return "R";
            case UPLEFTROOF:
                return ">";
            case UPRIGHTROOF:
                return "<";
            case DOWNLEFTROOF:
                return "(";
            case DOWNRIGHTROOF:
                return ")";
            case BASEROOF:
                return "B";
            case SHOOTABLEWALL:
                return "O";
            case NEXTLEVEL:
                return "N";
            case CLOSEDDOOR:
                return "X";
            case OPENDOOR:
                return "|";
            case OPENDOOR2:
                return "M";
            case TELEPORTKEY:
                return "t";
        }
        return "";
    }

    public static Cell stringToCell(GameMap map, char car, int x, int y) {
        switch (car) {
            case '#':
                return new Cell(map, x, y, CellType.WALL);
            case '.':
                return new Cell(map, x, y, CellType.FLOOR);
            case ',':
                return new Cell(map, x, y, CellType.GRASS);
            case '?':
                return new Cell(map, x, y, CellType.TREE);
            case ';':
                return new Cell(map, x, y, CellType.BUSH);
            case ' ':
                return new Cell(map, x, y, CellType.EMPTY);
            case 'U':
                return new Cell(map, x, y, CellType.UPROOF);
            case 'D':
                return new Cell(map, x, y, CellType.DOWNROOF);
            case 'L':
                return new Cell(map, x, y, CellType.LEFTROOF);
            case 'R':
                return new Cell(map, x, y, CellType.RIGHTROOF);
            case '>':
                return new Cell(map, x, y, CellType.UPLEFTROOF);
            case '<':
                return new Cell(map, x, y, CellType.UPRIGHTROOF);
            case '(':
                return new Cell(map, x, y, CellType.DOWNLEFTROOF);
            case ')':
                return new Cell(map, x, y, CellType.DOWNRIGHTROOF);
            case 'B':
                return new Cell(map, x, y, CellType.BASEROOF);
            case 'O':
                return new Cell(map, x, y, CellType.SHOOTABLEWALL);
            case 'N':
                return new Cell(map, x, y, CellType.NEXTLEVEL);
            case 'X':
                return new Cell(map, x, y, CellType.CLOSEDDOOR);
            case '|':
                return new Cell(map, x, y, CellType.OPENDOOR);
            case 'M':
                return new Cell(map, x, y, CellType.OPENDOOR2);
            case 't':
                return new Cell(map, x, y, CellType.TELEPORTKEY);
        }
        return new Cell(map, x, y, CellType.EMPTY);
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

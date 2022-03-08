package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.GameMap;

public abstract class Actor implements Drawable {
    protected boolean killable = false;
    protected int coolDownTimer;
    protected GameMap map;
    protected int coolDown = 0;

    protected Cell cell;
    protected int health = 10;

    public Actor(Cell cell, GameMap map) {
        this.map = map;
        this.cell = cell;
        this.cell.setActor(this);
    }

    abstract public void act();

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) { this.health = health; }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public boolean isKillable() {return killable; }
}

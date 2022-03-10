package com.codecool.dungeoncrawl.logic.actors.inmovable;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Boss extends Actor {
    public Boss(Cell cell, GameMap map) {
        super(cell, map);
    }

    @Override
    public String getTileName() {
        return null;
    }

    @Override
    public void act() {

    }
}

package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Portal extends Actor implements Killable{
    String type;
    Direction direction;


    public Portal(Cell cell, GameMap map, Direction direction, String type) {
        super(cell, map);
        this.direction = direction;
        this.type = type;
    }

    public void killPortal() {
        map.setPortal(type, null);
        kill(map, this);
    }

    public void act() {

    }

    @Override
    public String getTileName() {
        if (type.equals("red"))
            return "redPortal";
        else
            return "bluePortal";
    }
}

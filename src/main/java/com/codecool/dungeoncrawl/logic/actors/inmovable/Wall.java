package com.codecool.dungeoncrawl.logic.actors.inmovable;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Killable;

public class Wall extends Actor implements Killable {
    private String type;

    public Wall(GameMap map, Cell cell, String type) {
        super(cell, map);
        this.type = type;
        health = 50;
        killable = true;
    }

    @Override
    public String getTileName() {
        return "wall" + type;
    }

    @Override
    public void act() {
        if(isDead(this)) kill(map, this);
    }
}

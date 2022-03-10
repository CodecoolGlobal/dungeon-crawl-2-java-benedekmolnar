package com.codecool.dungeoncrawl.logic.actors.movable.monster;


import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Direction;

public class Ghost extends Monster {
    public Ghost(GameMap map, Cell cell, Direction direction) {
        super(map, cell, direction, 3);
        killable = false;
        coolDownTimer = 10;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    @Override
    public void act() {
        action();
    }

    @Override
    protected void changeDir() {

    }
}

package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Movable {
    private char lastOrder;

    public Player(Cell cell) {
        super(cell, Direction.UP);
        lastOrder = ' ';
        super.coolDownTimer = 3;
    }

    public String getTileName() {
        return "player";
    }

    public void act() {
        if (super.coolDown == 0) {
            if (lastOrder == 'w') changeDirection(Direction.UP);
            if (lastOrder == 'd') changeDirection(Direction.RIGHT);
            if (lastOrder == 's') changeDirection(Direction.DOWN);
            if (lastOrder == 'a') changeDirection(Direction.LEFT);
            if (lastOrder == 'y') shoot();

            moveToDir();

            lastOrder = ' ';
            coolDown = coolDownTimer;
        }
        else coolDown--;
    }

    private void shoot() {

    }

    public void setLastOrder(char order) {
        lastOrder = order;
    }
}

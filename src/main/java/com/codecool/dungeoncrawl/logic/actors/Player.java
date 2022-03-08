package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    private char lastOrder;

    public Player(Cell cell) {
        super(cell);
        lastOrder = ' ';
        super.coolDownTimer = 3;
    }

    public String getTileName() {
        return "player";
    }

    public void act() {
        if (super.coolDown == 0) {
            if (lastOrder == 'w') this.move(0, -1);
            if (lastOrder == 'd') this.move(1, 0);
            if (lastOrder == 's') this.move(0, 1);
            if (lastOrder == 'a')  this.move(-1, 0);

            lastOrder = ' ';
            coolDown = coolDownTimer;
        }
        else coolDown--;
    }

    public void setLastOrder(char order) {
        lastOrder = order;
    }
}

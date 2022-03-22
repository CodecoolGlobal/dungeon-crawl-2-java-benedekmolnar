package com.codecool.dungeoncrawl.logic.actors.movable.monster;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.Killable;
import com.codecool.dungeoncrawl.logic.actors.movable.Movable;
import com.codecool.dungeoncrawl.logic.actors.movable.player.Player;

public abstract class Monster extends Movable {
    private final int damage;

    public Monster(GameMap map, Cell cell, Direction direction, int damage) {
        super(map, cell, direction);
        this.damage = damage;
    }

    private void attack() {
        Player player = map.getPlayer();
        if (cell.getNeighborByDir(direction).getActor() != null &&
            cell.getNeighborByDir(direction).getActor().equals(player)) {
            player.changeHealth(damage * -1);
        }
    }

    public void action() {
        if (coolDown == 0) {
            attack();
            moveToDir();
            changeDir();
            coolDown = coolDownTimer;
        }
        else coolDown--;
    }

    abstract protected void changeDir();
}

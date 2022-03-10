package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public interface Killable {
    default void kill(GameMap map, Actor actor) {
        map.removeFromActors(actor);
        actor.getCell().setActor(null);
    }
    default boolean isDead(Actor actor) {
        return actor.getHealth() <= 0;
    }
}

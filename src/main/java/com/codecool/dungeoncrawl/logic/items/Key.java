package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Key extends Item{
    public Key(GameMap map, Cell cell) {
        super(map, cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}

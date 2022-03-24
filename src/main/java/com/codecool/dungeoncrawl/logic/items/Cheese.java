package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Cheese extends Item{
    public Cheese(GameMap map, Cell cell) {
        super(map, cell);
    }

    @Override
    public String getTileName() {
        return "cheese";
    }
}

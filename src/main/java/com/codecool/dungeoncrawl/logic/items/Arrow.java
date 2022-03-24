package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public class Arrow extends Item{
    public Arrow(GameMap map, Cell cell) {
        super(map, cell);
    }

    @Override
    public String getTileName() {
        return "arrow";
    }
}

package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Arrow extends Item{
    public Arrow(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "arrow";
    }
}

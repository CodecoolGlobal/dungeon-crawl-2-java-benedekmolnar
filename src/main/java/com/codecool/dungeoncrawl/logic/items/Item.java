package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.GameMap;

public abstract class Item implements Drawable {
    private Cell cell;
    private GameMap map;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Item(GameMap map, Cell cell) {
        this.cell = cell;
        this.map = map;
        this.cell.setItem(this);
        map.getItems().add(this);
    }


}

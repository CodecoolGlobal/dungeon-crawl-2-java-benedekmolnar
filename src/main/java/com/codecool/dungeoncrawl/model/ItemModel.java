package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Arrow;
import com.codecool.dungeoncrawl.logic.items.Cheese;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;

public class ItemModel {
    private String type;
    private final int x;
    private final int y;

    public ItemModel(Item item) {
        if (item instanceof Arrow)
            type = "arrow";
        if (item instanceof Key)
            type = "key";
        if (item instanceof Cheese)
            type = "cheese";

        x = item.getCell().getX();
        y = item.getCell().getY();
    }

    public ItemModel(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public Item createItemFromModel(GameMap map) {
        Cell itemCell = map.getCell(x, y);
        switch (type) {
            case "arrow":
                return new Arrow(map, itemCell);
            case "key":
                return new Key(map, itemCell);
        }
        return new Cheese(map, itemCell);
    }

    public String getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

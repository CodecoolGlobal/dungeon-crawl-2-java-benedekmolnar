package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    @Override
    public String getTileName() {
        return "item";
    }
}

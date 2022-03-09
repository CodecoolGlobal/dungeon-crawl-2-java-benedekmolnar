package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    UPROOF("uproof"),
    DOWNROOF("downroof"),
    LEFTROOF("leftroof"),
    RIGHTROOF("rightroof"),
    UPRIGHTROOF("uprightroof"),
    UPLEFTROOF("upleftroof"),
    DOWNRIGHTROOF("downrightroof"),
    DOWNLEFTROOF("downleftroof"),
    WALL("wall"),
    BASEROOF("baseroof"),
    SHOOTABLEWALL("shootablewall"),
    BLUEPORTAL("bluePortal"),
    TREE("tree"),
    BUSH("bush"),
    GRASS("grass"),
    REDPORTAL("redPortal");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}

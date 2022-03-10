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
    REDPORTAL("redPortal"),
    NEXTLEVEL("nextlevel"),
    TREE("tree"),
    BUSH("bush"),
    GRASS("grass"),
    OPENDOOR("opendoor"),
    OPENDOOR2("opendoor2"),
    CLOSEDDOOR("closeddoor"),
    TELEPORTKEY("teleportkey");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}

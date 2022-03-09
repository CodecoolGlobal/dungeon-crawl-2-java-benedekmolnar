package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    UPWALL("upwall"),
    DOWNWALL("downwall"),
    LEFTWALL("leftwall"),
    RIGHTWALL("rightwall"),
    UPRIGHTCORNER("uprightcorner"),
    UPLEFTCORNER("upleftcorner"),
    DOWNRIGHTCORNER("downrightcorner"),
    DOWNLEFTCORNER("downleftcorner"),
    WALL("wall"),
    SHOOTABLEWALL("shootablewall"),
    BLUEPORTAL("bluePortal"),
    REDPORTAL("redPortal"),
    NEXTLEVEL("nextlevel");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}

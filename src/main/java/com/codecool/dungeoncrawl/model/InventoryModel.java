package com.codecool.dungeoncrawl.model;

public class InventoryModel {
    private int gameStateId;
    private int arrow;
    private int key;

    public InventoryModel(int gameStateId, int arrow, int key) {
        this.gameStateId = gameStateId;
        this.arrow = arrow;
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getArrow() {
        return arrow;
    }

    public void setArrow(int arrow) {
        this.arrow = arrow;
    }

    public int getGameStateId() {
        return gameStateId;
    }

    public void setGameStateId(int gameStateId) {
        this.gameStateId = gameStateId;
    }
}

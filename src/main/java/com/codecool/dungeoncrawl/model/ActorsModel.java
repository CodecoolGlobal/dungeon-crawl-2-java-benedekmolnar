package com.codecool.dungeoncrawl.model;

public class ActorsModel extends BaseModel{
    private int gameStateId;
    private String type;
    private int x;
    private int y;
    private int hp;
    private String text;


    public ActorsModel(int gameStateId, String type, int x, int y, int hp, String text) {
        this.gameStateId = gameStateId;
        this.type = type;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGameStateId() {
        return gameStateId;
    }

    public void setGameStateId(int gameStateId) {
        this.gameStateId = gameStateId;
    }
}

package com.codecool.dungeoncrawl.model;

public class ActorsModel extends BaseModel{
    private int gameStateId;
    private String type;
    private int x;
    private int y;
    private int hp;
    private String direction;
    private String data;
    private int cooldown_timer;


    public ActorsModel(int gameStateId, String type, int x, int y, int hp, String direction, String data, int cooldown_timer) {
        this.gameStateId = gameStateId;
        this.type = type;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.direction = direction;
        this.data = data;
        this.cooldown_timer = cooldown_timer;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

    public int getCooldown_timer() {
        return cooldown_timer;
    }

    public void setCooldown_timer(int cooldown_timer) {
        this.cooldown_timer = cooldown_timer;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

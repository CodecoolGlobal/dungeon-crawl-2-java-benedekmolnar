package com.codecool.dungeoncrawl.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class GameState extends BaseModel {
    private Date savedAt;
    private String currentMap;
    private String playerName;

    public GameState(String currentMap, Date savedAt, String playerName) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.playerName = playerName;
    }

    public GameState(int id, String currentMap, Date savedAt, String playerName) {
        this.id = id;
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.playerName = playerName;
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}

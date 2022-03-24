package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;

import java.sql.Date;
import java.util.List;

public interface GameStateDao {
    void add(GameState state);
    void update(String currentMap, Date savedAt, int id);
    GameState get(String playerName);
    List<GameState> getAll();

    boolean isPlayerNameInDatabase(String playerName);
}

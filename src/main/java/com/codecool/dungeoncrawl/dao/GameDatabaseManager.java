package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.movable.player.Player;
import com.codecool.dungeoncrawl.model.ActorsModel;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ItemsModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class GameDatabaseManager {
    private GameStateDao gameStateDao;
    private ActorsDaoJdbc actorsDao;
    private ItemsDaoJdbc itemsDao;
    private InventoryDaoJdbc inventoryDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        gameStateDao = new GameStateDaoJdbc(dataSource);
        actorsDao = new ActorsDaoJdbc(dataSource);
        itemsDao = new ItemsDaoJdbc(dataSource);
        inventoryDao = new InventoryDaoJdbc(dataSource);
    }

    public int saveGameState(String currentMap, Date savedAt, String playerName) {
        GameState model = new GameState(currentMap, savedAt, playerName);
        gameStateDao.add(model);
        int gameStateId = model.getId();
        return gameStateId;
    }

    public void saveActors(List<ActorsModel> actorsModels, int gameStateId) {
        for (ActorsModel model: actorsModels) {
            actorsDao.add(model, gameStateId);
        }
    }

    public void saveItems(List<ItemsModel> itemsModels, int gameStateId) {
        for (ItemsModel model: itemsModels) {
            itemsDao.add(model, gameStateId);
        }
    }

    public void saveInventory(InventoryModel model, int gameStateId) {
        inventoryDao.add(model, gameStateId);
    }

    public int updateGameState(String currentMap, Date savedAt, String playerName){
        GameState model = new GameState(currentMap, savedAt, playerName);
        gameStateDao.update(model);
        return model.getId();
    }

    public void updateActors(List<ActorsModel> actorsModels, int gameStateId){
        for (ActorsModel model: actorsModels) {
            actorsDao.update(model, gameStateId);
        }
    }

    public void updateItems(List<ItemsModel> itemsModels, int gameStateId) {
        for (ItemsModel model: itemsModels) {
            itemsDao.update(model, gameStateId);
        }
    }

    public void updateInventory(InventoryModel model, int gameStateId) {
        inventoryDao.update(model, gameStateId);
    }

    public GameState loadGameState(String name) {
        return gameStateDao.get(name);
    }


    public boolean isPlayerNameInDatabase(String playerName) {
        return gameStateDao.isPlayerNameInDatabase(playerName);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("PSQL_DB_NAME");
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}

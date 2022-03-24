package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.movable.player.Player;
import com.codecool.dungeoncrawl.logic.items.Arrow;
import com.codecool.dungeoncrawl.model.ActorsModel;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;
    private ActorsDaoJdbc actorsDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
        actorsDao = new ActorsDaoJdbc(dataSource);
    }

    public void savePlayer(Player player) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model);
    }

    public int saveGameState(String currentMap, Date savedAt, String playerName) {
        GameState model = new GameState(currentMap, savedAt, playerName);
        gameStateDao.add(model);
        int gameStateId = model.getId();
        return gameStateId;
    }

    public void saveActors(List<ActorsModel> actorsModels, int gameStateId) {
        for (ActorsModel model: actorsModels) {
            actorsDao.add(model,  gameStateId);
        }
        //TODO: actordDao.add(), itemsDao.add(), inventoryDao.add();
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

package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(GameState state) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (player_name, map, saved_at) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, state.getPlayerName());
            st.setString(2, state.getCurrentMap());
            st.setDate(3, state.getSavedAt());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next(); // Read next returned value - in this case the first one. See ResultSet docs for more explaination
            state.setId(rs.getInt(1));

        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Author.", throwables);
        }
    }

    @Override
    public void update(String currentMap, Date savedAt, int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE game_state SET  map = ?, saved_at = ? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, currentMap);
            st.setDate(2, savedAt);
            st.setInt(3, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState get(String playerName) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, player_name, map, saved_at FROM game_state WHERE player_name = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, playerName);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) { // first row was not found == no data was returned by the query
                return null;
            }
            GameState gameState = new GameState(rs.getString(3), rs.getDate(4), rs.getString(2));
            gameState.setId(rs.getInt(1)); // we already knew author id, so we do not read it from database.
            return gameState;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GameState> getAll() {
        return null;
    }

    @Override
    public boolean isPlayerNameInDatabase(String playerName){
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT 1 FROM game_state WHERE player_name = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, playerName);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ActorsModel;
import com.codecool.dungeoncrawl.model.GameState;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ActorsDaoJdbc {
    private DataSource dataSource;

    public ActorsDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(ActorsModel actor, int gameStateId) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO actors (game_state_id, type, hp, x, y, direction, data, cooldown_timer) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, gameStateId);
            st.setString(2, actor.getType());
            st.setInt(3, actor.getHp());
            st.setInt(4, actor.getX());
            st.setInt(5, actor.getY());
            st.setString(6, actor.getDirection());
            st.setString(7, actor.getData());
            st.setInt(8, actor.getCooldown_timer());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            actor.setId(rs.getInt(1));

        } catch (SQLException throwables) {
            throw new RuntimeException("Error", throwables);
        }
    }

    public void delete( int gameStateId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM actors WHERE game_state_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, gameStateId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ActorsModel> get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            List<ActorsModel> actors = new LinkedList<>();
            String sql = "SELECT * FROM actors WHERE game_state_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                actors.add(new ActorsModel(
                        rs.getString(3),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(9),
                        rs.getInt(4),
                        rs.getString(8)));
            }
            return actors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ActorsModel;

import javax.sql.DataSource;
import java.sql.*;
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
            rs.next(); // Read next returned value - in this case the first one. See ResultSet docs for more explaination
            actor.setId(rs.getInt(1));

        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Author.", throwables);
        }
    }

    public void update(ActorsModel actor, int gameStateId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE actors SET  type = ?, x = ?, y = ?, hp = ?, direction = ?, data = ?, cooldown_timer = ? WHERE game_state_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, actor.getType());
            st.setInt(2, actor.getX());
            st.setInt(3, actor.getY());
            st.setInt(4, actor.getHp());
            st.setString(5, actor.getDirection());
            st.setString(6, actor.getData());
            st.setInt(7, actor.getCooldown_timer());
            st.setInt(8, gameStateId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    public ActorsModel get(int id) {
        return null;
    }

    public List<ActorsModel> getAll() {
        return null;
    }
}

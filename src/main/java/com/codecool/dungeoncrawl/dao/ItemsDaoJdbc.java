package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemsModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ItemsDaoJdbc {
    private DataSource dataSource;

    public ItemsDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(ItemsModel item, int gameSateId) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO items (game_state_id, type, x, y) VALUES (?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, gameSateId);
            st.setString(2, item.getType());
            st.setInt(3, item.getX());
            st.setInt(4, item.getY());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));

        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Author.", throwables);
        }
    }

    public void update(ItemsModel item, int gameStateId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE items SET type = ?, x = ?, y = ? WHERE game_state_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, item.getType());
            st.setInt(2, item.getX());
            st.setInt(3, item.getY());
            st.setInt(4, gameStateId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int gameStateId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM items WHERE game_state_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, gameStateId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ItemsModel> get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            List<ItemsModel> items = new LinkedList<>();
            String sql = "SELECT * FROM items WHERE game_state_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                items.add(new ItemsModel(rs.getString(3), rs.getInt(4), rs.getInt(5)));
            }
            return items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ItemsModel> getAll() {
        return null;
    }
}

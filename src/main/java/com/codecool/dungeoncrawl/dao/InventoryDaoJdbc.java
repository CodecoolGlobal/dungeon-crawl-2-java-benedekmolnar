package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class InventoryDaoJdbc {
    private DataSource dataSource;

    public InventoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(InventoryModel inventory, int gameSateId) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO inventory (game_state_id, arrow, key) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, gameSateId);
            st.setInt(2, inventory.getArrow());
            st.setInt(3, inventory.getKey());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            inventory.setId(rs.getInt(1));

        } catch (SQLException throwables) {
            throw new RuntimeException("Error", throwables);
        }
    }

    public void update(InventoryModel inventory, int gameStateId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE inventory SET arrow = ?, key = ? WHERE game_state_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, inventory.getArrow());
            st.setInt(2, inventory.getKey());
            st.setInt(3, gameStateId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public InventoryModel get(int id) {
        return null;
    }
}

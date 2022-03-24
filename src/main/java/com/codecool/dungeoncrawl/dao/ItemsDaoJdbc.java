package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ItemsModel;

import javax.sql.DataSource;
import java.sql.*;
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

    public void update(ItemsModel item) {

    }

    public ItemsModel get(int id) {
        return null;
    }

    public List<ItemsModel> getAll() {
        return null;
    }
}

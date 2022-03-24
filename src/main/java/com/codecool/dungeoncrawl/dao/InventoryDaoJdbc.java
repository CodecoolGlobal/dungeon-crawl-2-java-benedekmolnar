package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ActorsModel;
import com.codecool.dungeoncrawl.model.InventoryModel;

import javax.sql.DataSource;
import java.util.List;

public class InventoryDaoJdbc {
    private DataSource dataSource;

    public InventoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(InventoryModel inventory) {

    }

    public void update(InventoryModel inventory) {

    }

    public InventoryModel get(int id) {
        return null;
    }

    public List<InventoryModel> getAll() {
        return null;
    }
}

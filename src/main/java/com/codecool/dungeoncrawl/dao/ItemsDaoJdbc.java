package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ActorsModel;
import com.codecool.dungeoncrawl.model.ItemsModel;

import javax.sql.DataSource;
import java.util.List;

public class ItemsDaoJdbc {
    private DataSource dataSource;

    public ItemsDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(ItemsModel item) {

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

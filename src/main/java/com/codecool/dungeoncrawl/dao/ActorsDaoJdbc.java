package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.ActorsModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class ActorsDaoJdbc {
    private DataSource dataSource;

    public ActorsDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(ActorsModel actor) {

    }

    public void update(ActorsModel actor) {

    }

    public ActorsModel get(int id) {
        return null;
    }

    public List<ActorsModel> getAll() {
        return null;
    }
}

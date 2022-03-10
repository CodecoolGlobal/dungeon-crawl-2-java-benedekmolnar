package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.movable.player.Player;
import com.codecool.dungeoncrawl.logic.actors.inmovable.Portal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Portal redPortal = null;
    private Portal bluePortal = null;

    private Player player;
    private List<Actor> actors = new LinkedList<>();
    private List<Actor> added = new LinkedList<>();
    private List<Actor> killed = new LinkedList<>();

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public List<Cell> getFreeCells() {
        List<Cell> freeCells = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (cells[x][y].getType() == CellType.FLOOR &&
                    cells[x][y].getActor() == null)
                    freeCells.add(cells[x][y]);
            }
        }
        return freeCells;
    }

    public void addToActors(Actor actor) {
        added.add(actor);
    }

    public void removeFromActors(Actor actor) {
        killed.add(actor);
    }

    public void actActors() {
        actors.forEach(Actor::act);
        actors.addAll(added);
        actors.removeAll(killed);
        added.clear();
        killed.clear();
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Portal getPortal(String type) {
        if (type.equals("red")) return redPortal;
        else return bluePortal;
    }

    public void setPortal(String type, Portal portal) {
        if (type.equals("red")) redPortal = portal;
        else bluePortal = portal;
    }

    public List<Actor> getActors() {
        return actors;
    }
}

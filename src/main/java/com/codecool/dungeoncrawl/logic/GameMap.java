package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.movable.player.Player;
import com.codecool.dungeoncrawl.logic.actors.inmovable.Portal;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.ItemsModel;
import com.codecool.dungeoncrawl.model.ActorsModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Portal redPortal = null;
    private Portal bluePortal = null;

    private Player player;
    private List<Actor> actors = new LinkedList<>();
    private List<Item> items = new LinkedList<>();
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

    public List<Item> getItems() {
        return items;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public String saveMap() {
        String map = Arrays.stream(cells).map(this::addLineToString).reduce("", (mapStr, lineStr) -> mapStr + "\n" + lineStr);
        return map.substring(1, map.length());
    }

    private String addLineToString(Cell[] line) {
        return Arrays.stream(line).map(Cell::typeToString).reduce("", (lineStr, cellStr) -> lineStr + cellStr);
    }

    public void loadMap(String mapStr) {
        String[] lines = mapStr.split("\n");
        for(int i = 0; i < lines.length; i++) {
            char[] line = lines[i].toCharArray();
            for (int j = 0; j < line.length; j++) {
                cells[i][j] = Cell.stringToCell(this, line[j], i, j);
            }
        }
    }

    public List<ActorsModel> saveActors() {
        return actors.stream().map(ActorsModel::new).collect(Collectors.toList());
    }

    public void loadActors(List<ActorsModel> data) {
        actors = data.stream().map(d -> d.createActorFromData(this)).collect(Collectors.toList());
        player = (Player) actors.stream().filter(p -> p instanceof Player).findFirst().get();
    }

    public List<ItemsModel> saveItems() {
        return items.stream().map((ItemsModel::new)).collect(Collectors.toList());
    }

    public void loadItems(List<ItemsModel> data) {
        items = data.stream().map(d -> d.createItemFromModel(this)).collect(Collectors.toList());
    }

    public InventoryModel saveInventory() {
        return new InventoryModel(player.getInventory());
    }

    public void loadInventory(InventoryModel data) {
        player.setInventory(data.createInventoryDir());
    }
}

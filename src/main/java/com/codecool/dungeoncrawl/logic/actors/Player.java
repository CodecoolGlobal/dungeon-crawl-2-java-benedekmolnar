package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.Map;

public class Player extends Actor {
    private Map<String, Integer> inventory = new HashMap<>();

    public Player(Cell cell) {
        super(cell);
    }

    public Map<String, Integer> getInventory(){
        return inventory;
    }

    public void addItemToInventory(String item){
        if (inventory.get(item) != null){
            inventory.put(item, inventory.get(item) + 1);
        }else{
            inventory.put(item, 1);
        }
    }

    public String getTileName() {
        return "player";
    }
}

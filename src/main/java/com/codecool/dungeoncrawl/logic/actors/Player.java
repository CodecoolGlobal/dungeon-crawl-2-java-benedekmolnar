package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;


import java.util.HashMap;
import java.util.Map;

public class Player extends Actor {
    private Map<String, Integer> inventory = new HashMap<>();

    public Player(Cell cell) {
        super(cell);
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

    public String inventoryToString() {
        StringBuilder inventoryAsString = new StringBuilder();
        for (String key : inventory.keySet()) {
            inventoryAsString.append(key + "=" + inventory.get(key) + ", ");
        }
        return inventoryAsString.toString();
    }
}

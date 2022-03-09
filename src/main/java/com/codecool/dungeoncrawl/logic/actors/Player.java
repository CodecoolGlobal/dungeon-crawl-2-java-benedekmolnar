package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Cheese;
import com.codecool.dungeoncrawl.logic.items.Item;



import java.util.HashMap;
import java.util.Map;

public class Player extends Actor {
    private Map<Item, Integer> inventory = new HashMap<>();

    public Player(Cell cell) {
        super(cell);
    }

    public void pickUpItem() {
        Cell currentCell = this.getCell();
        Item itemToPickUp = currentCell.getItem();
        if (itemToPickUp instanceof Cheese){
            changeHealth(2);
        }else{
            this.addItemToInventory(itemToPickUp);
        }
        currentCell.setItem(null);
    }

    public void addItemToInventory(Item item){
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
        for (Item key : inventory.keySet()) {
            inventoryAsString.append(key.getTileName() + "=" + inventory.get(key) + "   ");
            inventoryAsString.append("\n");
        }
        return inventoryAsString.toString();
    }
}

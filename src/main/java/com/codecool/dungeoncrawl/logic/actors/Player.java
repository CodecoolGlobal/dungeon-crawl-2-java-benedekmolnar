package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Cheese;
import com.codecool.dungeoncrawl.logic.items.Item;



import java.util.HashMap;
import java.util.Map;

public class Player extends Actor {
    private Map<String, Integer> inventory = new HashMap<>();

    public Player(Cell cell) {
        super(cell);
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        boolean nextIsClosedDoorAndHasKey = nextCell.getType() == CellType.CLOSEDDOOR && inventory.get("key") != null;
        boolean nextIsFloor = nextCell.getType() == CellType.FLOOR;
        boolean nextIsNotActor = nextCell.getActor() == null;
        if ((nextIsFloor && nextIsNotActor) || nextIsClosedDoorAndHasKey) {
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }
    }

    public void pickUpItem() {
        Cell currentCell = this.getCell();
        Item itemToPickUp = currentCell.getItem();
        if (itemToPickUp instanceof Cheese){
            changeHealth(2);
        }else{
            this.addItemToInventory(itemToPickUp.getTileName());
        }
        currentCell.setItem(null);
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
            inventoryAsString.append(key + "=" + inventory.get(key) + "   ");
            inventoryAsString.append("\n");
        }
        return inventoryAsString.toString();
    }
}

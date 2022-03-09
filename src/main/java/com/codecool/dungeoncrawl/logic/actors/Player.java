package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.Cheese;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Player extends Movable implements Killable {
    private Map<Item, Integer> inventory = new HashMap<>();
    private char lastOrder;


    public Player(GameMap map, Cell cell) {
        super(map, cell, Direction.UP);
        killable = true;
        lastOrder = ' ';
        super.coolDownTimer = 3;
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

    public void act() {
        if (isDead(this)) kill(map, this);
        if (super.coolDown == 0) {
            switch (lastOrder) {
                case 'w':
                    changeDirection(Direction.UP);
                    moveToDir();
                    break;
                case 'd':
                    changeDirection(Direction.RIGHT);
                    moveToDir();
                    break;
                case 's':
                    changeDirection(Direction.DOWN);
                    moveToDir();
                    break;
                case 'a':
                    changeDirection(Direction.LEFT);
                    moveToDir();
                    break;
                case 'y':
                    shoot();
                    break;
                case 'r':
                    redShoot();
                    break;
                case 'b':
                    blueShoot();
                    break;
            }
            lastOrder = ' ';
            coolDown = coolDownTimer;
        }
        else coolDown--;
    }

    private void shoot() {
        if (cell.getNeighborByDir(direction).getType() == CellType.FLOOR &&
            cell.getNeighborByDir(direction).getActor() == null)
            map.addToActors(new Projectile(map, cell.getNeighborByDir(direction), direction));
    }

    private void redShoot() {
        if (cell.getNeighborByDir(direction).getType() == CellType.FLOOR &&
            cell.getNeighborByDir(direction).getActor() == null) {

            if (map.getPortal("red") != null)
                map.getPortal("red").killPortal();

            map.addToActors(new PortalProjectile(map, cell.getNeighborByDir(direction), direction, "red"));
        };

    }

    private void blueShoot() {
        if (cell.getNeighborByDir(direction).getType() == CellType.FLOOR &&
            cell.getNeighborByDir(direction).getActor() == null) {

            if (map.getPortal("blue") != null)
                map.getPortal("blue").killPortal();

            map.addToActors(new PortalProjectile(map, cell.getNeighborByDir(direction), direction, "blue"));
        }
    }

    public void setLastOrder(char order) {
        lastOrder = order;
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

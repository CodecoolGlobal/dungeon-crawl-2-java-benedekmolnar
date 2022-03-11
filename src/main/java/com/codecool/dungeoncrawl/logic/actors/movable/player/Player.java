package com.codecool.dungeoncrawl.logic.actors.movable.player;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.Killable;
import com.codecool.dungeoncrawl.logic.actors.movable.projectile.PortalProjectile;
import com.codecool.dungeoncrawl.logic.actors.movable.projectile.Projectile;
import com.codecool.dungeoncrawl.logic.actors.movable.Movable;
import com.codecool.dungeoncrawl.logic.items.Arrow;
import com.codecool.dungeoncrawl.logic.items.Cheese;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;

import java.util.HashMap;
import java.util.Map;

public class Player extends Movable implements Killable {
    private Map<String, Integer> inventory = new HashMap<>();
    private char lastOrder;


    public Player(GameMap map, Cell cell) {
        super(map, cell, Direction.UP);
        killable = true;
        health = 50;
        lastOrder = ' ';
        super.coolDownTimer = 3;
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        boolean nextIsClosedDoorAndHasKey = nextCell.getType() == CellType.CLOSEDDOOR && inventory.get("key") != null;
        boolean nextIsFloor = nextCell.getType() == CellType.FLOOR;
        boolean nextIsNotActor = nextCell.getActor() == null;
        boolean nextIsOpenDoor = nextCell.getType() == CellType.OPENDOOR;
        boolean nextIsNextLevel = nextCell.getType() == CellType.NEXTLEVEL;
        boolean nextIsBosslevel = nextCell.getType() == CellType.OPENDOOR2;
        boolean nextIsTeleportKey = nextCell.getType() == CellType.TELEPORTKEY;
        if ((nextIsFloor && nextIsNotActor) || nextIsClosedDoorAndHasKey
                || nextIsOpenDoor || nextIsNextLevel
                || nextIsBosslevel || nextIsTeleportKey) {
            if (nextCell.getType() == CellType.CLOSEDDOOR){
                nextCell.setType(CellType.OPENDOOR);
            }
            if (nextCell.getType() == CellType.TELEPORTKEY){
                addItemToInventory(new Key(getCell()));
            }
            getCell().setActor(null);
            nextCell.setActor(this);
            setCell(nextCell);
        }
    }

    public void pickUpItem() {
        Cell currentCell = this.getCell();
        Item itemToPickUp = currentCell.getItem();
        if (itemToPickUp != null){
            if (itemToPickUp instanceof Cheese){
                changeHealth(10);
            }else{
                this.addItemToInventory(itemToPickUp);
            }
            currentCell.setItem(null);
        }
    }

    public void addItemToInventory(Item item){
        int value;
        if (item instanceof Arrow){
            value = 20;
        }else{
            value = 1;
        }
        if (inventory.get(item.getTileName()) != null){
            inventory.put(item.getTileName(), inventory.get(item.getTileName()) + value);
        }else{
            inventory.put(item.getTileName(), value);
        }
    }

    public Map<String, Integer> getInventory(){
        return inventory;
    }

    public String getTileName() {
        return "player";
    }

    public void act() {
        if (isDead(this)) {
            kill(map, this);
            return;
        }
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
                    portalShoot("red");
                    break;
                case 'b':
                    portalShoot("blue");
                    break;
            }
            lastOrder = ' ';
            coolDown = coolDownTimer;
        }
        else coolDown--;
    }

    private void shoot() {
        if (inventory.get("arrow") != null){
            if (cell.getNeighborByDir(direction).getType() == CellType.FLOOR &&
                    cell.getNeighborByDir(direction).getActor() == null)
                map.addToActors(new Projectile(map, cell.getNeighborByDir(direction), direction));
            if (inventory.get("arrow") != 1){
                inventory.put("arrow", inventory.get("arrow") - 1);
            }else{
                inventory.remove("arrow");
            }
        }
    }

    private void portalShoot(String type) {
        if (cell.getNeighborByDir(direction).getType() == CellType.FLOOR &&
                cell.getNeighborByDir(direction).getActor() == null) {

            if (map.getPortal(type) != null)
                map.getPortal(type).killPortal();

            map.addToActors(new PortalProjectile(map, cell.getNeighborByDir(direction), direction, type));
        }
    }

    public void setLastOrder(char order) {
        lastOrder = order;
    }

    public String inventoryToString() {
        StringBuilder inventoryAsString = new StringBuilder();
        for (String key : inventory.keySet()) {
            inventoryAsString.append(key + "=" + inventory.get(key) + "    ");
        }
        return inventoryAsString.toString();
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }
}

package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.lang.reflect.Type;

public class PortalProjectile extends Projectile {
    private String type;


    public PortalProjectile(GameMap map, Cell cell, Direction direction, String type) {
        super(map, cell, direction);
        this.type = type;

    }



    public void act() {
        Cell nextCell = cell.getNeighborByDir(direction);
        Actor nextActor = nextCell.getActor();
        CellType nextType = nextCell.getType();
        if (nextActor != null)
            kill(map, this);
        if (nextType == CellType.WALL) {
            kill(map, this);
            Portal portal;
            if (type.equals("red")) {
                portal = new Portal(cell.getNeighborByDir(direction), map, direction, "red");
                map.setPortal("red", portal);
            }
            else {
                portal = new Portal(cell.getNeighborByDir(direction), map, direction, "blue");
                map.setPortal("blue", portal);
            }
            map.addToActors(portal);
        }
        moveToDir();
    }

    @Override
    public String getTileName() {
        if (type.equals("red"))
            return "redPortal";
        else
            return "bluePortal";
    }
}

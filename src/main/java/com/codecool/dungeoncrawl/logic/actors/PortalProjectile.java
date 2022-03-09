package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.Arrays;

public class PortalProjectile extends Projectile {
    private String type;
    private CellType[] walls = new CellType[]{CellType.WALL};


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
        if (Arrays.asList(walls).contains(nextType)) {
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

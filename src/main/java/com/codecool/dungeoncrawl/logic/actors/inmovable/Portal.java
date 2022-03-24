package com.codecool.dungeoncrawl.logic.actors.inmovable;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.Killable;
import com.codecool.dungeoncrawl.logic.actors.movable.player.Player;
import com.codecool.dungeoncrawl.logic.actors.movable.projectile.Projectile;

public class Portal extends Actor implements Killable {
    String type;
    Direction direction;


    public Portal(Cell cell, GameMap map, Direction direction, String type) {
        super(cell, map);
        this.direction = direction;
        this.type = type;
    }

    public void killPortal() {
        map.setPortal(type, null);
        kill(map, this);
    }

    public void act() {
        Cell portalCell = getPortalCell();
        Portal otherPortal = map.getPortal(getOtherType());
        if (portalCell.getActor() instanceof Player) {
            Player player = (Player) portalCell.getActor();
            if (player.getDirection().getReversDirection() == direction
                && otherPortal != null && otherPortal.isPortalCellFree()) {
                portalCell.setActor(null);
                player.setDirection(otherPortal.getDirection());
                player.setCell(otherPortal.getPortalCell());
                player.getCell().setActor(player);
            }
        }

        if (portalCell.getActor() instanceof Projectile) {
            Projectile projectile = (Projectile) portalCell.getActor();
            if (projectile.getDirection().getReversDirection() == direction
                    && otherPortal != null && otherPortal.isPortalCellFree()) {
                portalCell.setActor(null);
                projectile.setDirection(otherPortal.getDirection());
                projectile.setCell(otherPortal.getPortalCell());
                projectile.getCell().setActor(projectile);
            }
        }
    }

    public boolean isPortalCellFree() {
        return cell.getNeighborByDir(direction).getActor() == null;
    }

    public Cell getPortalCell() {
        return cell.getNeighborByDir(direction);
    }

    public String getOtherType() {
        if (type.equals("red")) return "blue";
        else return "red";
    }

    @Override
    public String getTileName() {
        if (type.equals("red"))
            return "redPortal";
        else
            return "bluePortal";
    }

    public Direction getDirection() {
        return direction;
    }

    public String getType() {
        return type;
    }
}

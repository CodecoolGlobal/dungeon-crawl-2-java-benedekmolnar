package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.inmovable.Boss;
import com.codecool.dungeoncrawl.logic.actors.inmovable.Cannon;
import com.codecool.dungeoncrawl.logic.actors.inmovable.Portal;
import com.codecool.dungeoncrawl.logic.actors.inmovable.Wall;
import com.codecool.dungeoncrawl.logic.actors.movable.monster.Ghost;
import com.codecool.dungeoncrawl.logic.actors.movable.monster.Knight;
import com.codecool.dungeoncrawl.logic.actors.movable.monster.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.movable.player.Player;
import com.codecool.dungeoncrawl.logic.actors.movable.projectile.PortalProjectile;
import com.codecool.dungeoncrawl.logic.actors.movable.projectile.Projectile;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ActorData {
    private String type;
    private int x;
    private int y;
    private Direction dir;
    private int coolDownTimer;
    private int health;
    private int def_x;
    private int def_y;
    private String color;

    public ActorData(Actor actor) {
        if (actor instanceof Skeleton)
            this.type = "skeleton";
        else if (actor instanceof Knight) {
            this.type = "knight";
            def_x = ((Knight) actor).getStartCell().getX();
            def_y = ((Knight) actor).getStartCell().getY();
        }
        else if (actor instanceof Ghost)
            this.type = "ghost";
        else if (actor instanceof Cannon)
            this.type = "cannon";
        else if (actor instanceof PortalProjectile) {
            this.type = "portalProjectile";
            color = ((PortalProjectile) actor).getType();
        }
        else if (actor instanceof Projectile)
            this.type = "projectile";
        else if (actor instanceof Wall)
            this.type = "wall";
        else if (actor instanceof Portal) {
            this.type = "portal";
            color = ((Portal) actor).getType();
        }
        else if (actor instanceof Boss)
            this.type = "boss";
        else if (actor instanceof Player)
            this.type = "player";

        x = actor.getCell().getX();
        y = actor.getCell().getY();
        dir = actor.getDirection();
        health = actor.getHealth();
        coolDownTimer = actor.getCoolDownTimer();
    }

    public Actor createActorFromData(GameMap map) {
        Cell actorCell = map.getCell(x, y);
        switch (type) {
            case "skeleton":
                Skeleton sk = new Skeleton(map, actorCell);
                sk.setHealth(health);
                sk.setCoolDownTimer(coolDownTimer);
                return sk;
            case "knight":
                Cell defCell = map.getCell(def_x, def_y);
                Knight kn = new Knight(map, actorCell);
                kn.setHealth(health);
                kn.setCoolDownTimer(coolDownTimer);
                kn.setStartCell(defCell);
                return kn;
            case "ghost":
                Ghost gs = new Ghost(map, actorCell, dir);
                gs.setHealth(health);
                gs.setCoolDownTimer(coolDownTimer);
                return gs;
            case "cannon":
                Cannon cn = new Cannon(map, actorCell);
                cn.setCoolDownTimer(coolDownTimer);
                return cn;
            case "portalProjectile":
                PortalProjectile pp = new PortalProjectile(map, actorCell, dir, color);
                return pp;
            case "projectile":
                Projectile pt = new Projectile(map, actorCell, dir);
                return pt;
            case "wall":
                Wall w = new Wall(map, actorCell, "1");
                w.setHealth(health);
                return w;
            case "portal":
                Portal p = new Portal(actorCell, map, dir, color);
                return p;
            case "boss":
                Boss b = new Boss(map, actorCell);
                b.setHealth(health);
                return b;
            case "player":
                Player pl = new Player(map, actorCell);
                pl.setHealth(health);
                pl.setDirection(dir);
                return pl;
        }
        return new Skeleton(map, actorCell);
    }
}

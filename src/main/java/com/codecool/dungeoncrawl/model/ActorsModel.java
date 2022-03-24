package com.codecool.dungeoncrawl.model;

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

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ActorsModel extends BaseModel{
    private int gameStateId;
    private String type;
    private int x;
    private int y;
    private int hp;
    private Direction direction;
    Map<String, String> data = new HashMap<>();
    private int cooldownTimer;


    public ActorsModel(Actor actor) {
        if (actor instanceof Skeleton)
            this.type = "skeleton";
        else if (actor instanceof Knight) {
            this.type = "knight";
            data.put("def_x", String.valueOf(((Knight) actor).getStartCell().getX()));
            data.put("def_y", String.valueOf(((Knight) actor).getStartCell().getY()));
        }
        else if (actor instanceof Ghost)
            this.type = "ghost";
        else if (actor instanceof Cannon)
            this.type = "cannon";
        else if (actor instanceof PortalProjectile) {
            this.type = "portalProjectile";
            data.put("color", ((PortalProjectile) actor).getType());
        }
        else if (actor instanceof Projectile)
            this.type = "projectile";
        else if (actor instanceof Wall) {
            this.type = "wall";
            data.put("type", ((Wall) actor).getType());
        }
        else if (actor instanceof Portal) {
            this.type = "portal";
            data.put("color", ((Portal) actor).getType());
        }
        else if (actor instanceof Boss) {
            this.type = "boss";
            data.put("skeleton_time", String.valueOf(((Boss) actor).getCoolDownForSkeletonTimer()));
            data.put("item_timer", String.valueOf(((Boss) actor).getCoolDownForItemTimer()));
            data.put("knight_timer", String.valueOf(((Boss) actor).getCoolDownForKnightTimer()));
        }
        else if (actor instanceof Player)
            this.type = "player";

        x = actor.getCell().getX();
        y = actor.getCell().getY();
        direction = actor.getDirection();
        hp = actor.getHealth();
        cooldownTimer = actor.getCoolDownTimer();
    }

    public ActorsModel(String type, int x, int y, String dir, int coolDownTimer, int health, String data) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.direction = Direction.dirFromString(dir);
        this.cooldownTimer = coolDownTimer;
        this.hp = health;
        this.data = createDataFromString(data);
    }

    public Actor createActorFromData(GameMap map) {
        Cell actorCell = map.getCell(x, y);
        switch (type) {
            case "skeleton":
                Skeleton sk = new Skeleton(map, actorCell);
                sk.setHealth(hp);
                sk.setCoolDownTimer(cooldownTimer);
                return sk;
            case "knight":
                Cell defCell = map.getCell(
                        Integer.parseInt(data.get("def_x")),
                        Integer.parseInt(data.get("def_y")));
                Knight kn = new Knight(map, actorCell);
                kn.setHealth(hp);
                kn.setCoolDownTimer(cooldownTimer);
                kn.setStartCell(defCell);
                return kn;
            case "ghost":
                Ghost gs = new Ghost(map, actorCell, direction);
                gs.setHealth(hp);
                gs.setCoolDownTimer(cooldownTimer);
                return gs;
            case "cannon":
                Cannon cn = new Cannon(map, actorCell);
                cn.setCoolDownTimer(cooldownTimer);
                return cn;
            case "portalProjectile":
                PortalProjectile pp = new PortalProjectile(map, actorCell, direction, data.get("color"));
                return pp;
            case "projectile":
                Projectile pt = new Projectile(map, actorCell, direction);
                return pt;
            case "wall":
                Wall w = new Wall(map, actorCell, "1");
                w.setHealth(hp);
                w.setType(data.get("type"));
                return w;
            case "portal":
                Portal p = new Portal(actorCell, map, direction, data.get("color"));
                return p;
            case "boss":
                Boss b = new Boss(map, actorCell);
                b.setHealth(hp);
                b.setCoolDownForItemTimer(Integer.parseInt(data.get("item_timer")));
                b.setCoolDownForKnightTimer(Integer.parseInt(data.get("knight_timer")));
                b.setCoolDownForSkeletonTimer(Integer.parseInt(data.get("skeleton_time")));
                return b;
            case "player":
                Player pl = new Player(map, actorCell);
                pl.setHealth(hp);
                pl.setDirection(direction);
                return pl;
        }
        return new Skeleton(map, actorCell);
    }

    private String createStringFromData() {
        StringJoiner sj = new StringJoiner(",");
        if (data == null){
            return null;
        }
        for (Map.Entry<String,String> entry : data.entrySet()) {
            sj.add(entry.getKey() + ":" + entry.getValue());
        }
        return sj.toString();
    }

    private Map<String, String> createDataFromString(String str) {
        Map<String, String> data = new HashMap<>();
        for (String d : str.split(",")) {
            String[] keyValue = d.split(":");
            data.put(keyValue[0], keyValue[1]);
        }
        return data;
    }


    public String getDirection() {
        return direction.dirString;
    }

    public int getHp() {
        return hp;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public String getType() {
        return type;
    }

    public int getGameStateId() {
        return gameStateId;
    }

    public void setGameStateId(int gameStateId) {
        this.gameStateId = gameStateId;
    }

    public int getCooldown_timer() {
        return cooldownTimer;
    }

    public String getData() {
        return createStringFromData();
    }
}

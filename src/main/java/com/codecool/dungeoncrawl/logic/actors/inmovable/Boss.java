package com.codecool.dungeoncrawl.logic.actors.inmovable;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.movable.monster.Knight;
import com.codecool.dungeoncrawl.logic.actors.movable.monster.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Arrow;
import com.codecool.dungeoncrawl.logic.items.Cheese;

import java.util.List;
import java.util.Random;

public class Boss extends Actor {
    private final int coolDownForSkeleton = 100;
    private int coolDownForSkeletonTimer = 100;
    private final int coolDownForKnight = 1000;
    private int coolDownForKnightTimer = 1000;
    private int coolDownForItem = 200;
    private int coolDownForKnightItem =  200;

    private Random rand = new Random();

    public Boss(GameMap map, Cell cell) {
        super(cell, map);


    }

    @Override
    public String getTileName() {
        return "boss";
    }

    public void killIfDead() {
        if ((int) map.getActors().stream().filter(e -> e instanceof Wall).count() == 0) {
            map.removeFromActors(this);
            this.getCell().setActor(null);
        }
    }

    @Override
    public void act() {
        if (coolDownForSkeletonTimer == 0) {
            coolDownForSkeletonTimer = coolDownForSkeleton;
            List<Cell> freeCells = map.getFreeCells();
            map.addToActors(new Skeleton(map, freeCells.get(rand.nextInt(freeCells.size()))));
        } else coolDownForSkeletonTimer--;
        if (coolDownForKnightTimer == 0) {
            coolDownForKnightTimer = coolDownForKnight;
            List<Cell> freeCells = map.getFreeCells();
            map.addToActors(new Knight(map, freeCells.get(rand.nextInt(freeCells.size()))));
        } else coolDownForKnightTimer--;
        if (coolDownForKnightItem == 0) {
            coolDownForKnightItem = coolDownForKnight;
            List<Cell> freeCells = map.getFreeCells();
            if (rand.nextInt(2) == 1) new Cheese(freeCells.get(rand.nextInt(freeCells.size())));
            else new Arrow(freeCells.get(rand.nextInt(freeCells.size())));
        } else coolDownForKnightItem--;
    }
}

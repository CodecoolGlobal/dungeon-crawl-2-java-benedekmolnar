package com.codecool.dungeoncrawl.logic.actors;

import java.util.Random;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, NONE;

    public Direction getReversDirection(){
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }
        return NONE;
    }

    public static Direction getRandDirection(){
        Random rand = new Random();
        int randInt = rand.nextInt(4);
        switch (randInt) {
            case 0:
                return UP;
            case 1:
                return RIGHT;
            case 2:
                return DOWN;
        }
        return LEFT;
    }
}

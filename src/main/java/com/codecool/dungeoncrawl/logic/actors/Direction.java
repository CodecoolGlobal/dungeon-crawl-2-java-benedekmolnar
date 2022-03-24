package com.codecool.dungeoncrawl.logic.actors;

import java.util.Random;

public enum Direction {
    UP("Up"), DOWN("Down"), LEFT("Left"), RIGHT("Right"), NONE("None");
    public final String dirString;

    Direction(String str) {
        dirString = str;
    }

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

    public static Direction dirFromString(String str){
        switch (str) {
            case "Up":
                return UP;
            case "Down":
                return DOWN;
            case "Left":
                return LEFT;
            case "Right":
                return RIGHT;
        }
        return NONE;
    }
}

package com.codecool.dungeoncrawl.logic.actors;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Direction getReversDirection(){
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
        }
        return LEFT;
    }
}

package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.inmovable.Boss;
import com.codecool.dungeoncrawl.logic.actors.inmovable.Cannon;
import com.codecool.dungeoncrawl.logic.actors.inmovable.Wall;
import com.codecool.dungeoncrawl.logic.actors.movable.monster.Ghost;
import com.codecool.dungeoncrawl.logic.actors.movable.monster.Knight;
import com.codecool.dungeoncrawl.logic.actors.movable.player.Player;
import com.codecool.dungeoncrawl.logic.actors.movable.monster.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Arrow;
import com.codecool.dungeoncrawl.logic.items.Cheese;
import com.codecool.dungeoncrawl.logic.items.Key;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(InputStream is) {
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Skeleton(map, cell));
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case 'U':
                            cell.setType(CellType.UPROOF);
                            break;
                        case 'D':
                            cell.setType(CellType.DOWNROOF);
                            break;
                        case '<':
                            cell.setType(CellType.UPLEFTROOF);
                            break;
                        case '>':
                            cell.setType(CellType.UPRIGHTROOF);
                            break;
                        case 'L':
                            cell.setType(CellType.LEFTROOF);
                            break;
                        case 'R':
                            cell.setType(CellType.RIGHTROOF);
                            break;
                        case '(':
                            cell.setType(CellType.DOWNLEFTROOF);
                            break;
                        case ')':
                            cell.setType(CellType.DOWNRIGHTROOF);
                            break;
                        case 'B':
                            cell.setType(CellType.BASEROOF);
                            break;
                        case 'M':
                            cell.setType(CellType.OPENDOOR2);
                            break;
                        case '?':
                            cell.setType(CellType.TREE);
                            break;
                        case ';':
                            cell.setType(CellType.BUSH);
                            break;
                        case ',':
                            cell.setType(CellType.GRASS);
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new Arrow(cell);
                            break;
                        case 'O':
                            cell.setType(CellType.SHOOTABLEWALL);
                            break;
                        case 'N':
                            cell.setType(CellType.NEXTLEVEL);
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            new Cheese(cell);
                            break;
                        case 'X':
                            cell.setType(CellType.CLOSEDDOOR);
                            break;
                        case '|':
                            cell.setType(CellType.OPENDOOR);
                            break;
                        case '@':
                            Player player = new Player(map, cell);
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(player);
                            map.addToActors(player);
                            break;
                        case 'l':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Knight(map, cell));
                            break;
                        case '↑':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Ghost(map, cell, Direction.UP));
                            break;
                        case '↓':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Ghost(map, cell, Direction.DOWN));
                            break;
                        case '→':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Ghost(map, cell, Direction.RIGHT));
                            break;
                        case '←':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Ghost(map, cell, Direction.LEFT));
                            break;
                        case '+':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Cannon(map, cell));
                            break;
                        case 't':
                            cell.setType(CellType.TELEPORTKEY);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Boss(map, cell));
                            break;
                        case '1':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Wall(map, cell, "1"));
                            break;
                        case '2':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Wall(map, cell, "2"));
                            break;
                        case '3':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Wall(map, cell, "3"));
                            break;
                        case '4':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Wall(map, cell, "4"));
                            break;
                        case '5':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Wall(map, cell, "5"));
                            break;
                        case '6':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Wall(map, cell, "6"));
                            break;
                        case '7':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Wall(map, cell, "7"));
                            break;
                        case '8':
                            cell.setType(CellType.FLOOR);
                            map.addToActors(new Wall(map, cell, "8"));
                            break;


                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}

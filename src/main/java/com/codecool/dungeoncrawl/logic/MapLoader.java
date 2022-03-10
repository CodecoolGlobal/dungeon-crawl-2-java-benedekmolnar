package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.movable.monster.Knight;
import com.codecool.dungeoncrawl.logic.actors.movable.player.Player;
import com.codecool.dungeoncrawl.logic.actors.movable.monster.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Arrow;
import com.codecool.dungeoncrawl.logic.items.Cheese;
import com.codecool.dungeoncrawl.logic.items.Key;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/memhaz.txt");
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
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new Arrow(cell);
                            break;
                        case 'O':
                            cell.setType(CellType.SHOOTABLEWALL);
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            new Cheese(cell);
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
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}

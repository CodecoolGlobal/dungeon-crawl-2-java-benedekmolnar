package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("uproof", new Tile(1, 16));
        tileMap.put("downroof", new Tile(1, 18));
        tileMap.put("leftroof", new Tile(0, 17));
        tileMap.put("rightroof", new Tile(2, 17));
        tileMap.put("upleftroof", new Tile(0, 16));
        tileMap.put("uprightroof", new Tile(2, 16));
        tileMap.put("downleftroof", new Tile(0, 18));
        tileMap.put("downrightroof", new Tile(2, 18));
        tileMap.put("baseroof", new Tile(1, 17));
        tileMap.put("floor", new Tile(3, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("projectile", new Tile(26, 24));
        tileMap.put("bluePortal", new Tile(14, 5));
        tileMap.put("redPortal", new Tile(7, 6));
        tileMap.put("key", new Tile(16,23));
        tileMap.put("arrow", new Tile(8,27));
        tileMap.put("cheese", new Tile(18,28));
        tileMap.put("opendoor", new Tile(2, 9));
        tileMap.put("closeddoor", new Tile(0, 9));
        tileMap.put("wall", new Tile(23, 27));
        tileMap.put("shootablewall", new Tile(23, 26));
        tileMap.put("nextlevel", new Tile(6, 9));
        tileMap.put("tree", new Tile(3, 1));
        tileMap.put("bush", new Tile(19, 5));
        tileMap.put("grass", new Tile(0, 2));
        tileMap.put("knight", new Tile(31, 0));
        tileMap.put("opendoor2", new Tile(2, 12));
        tileMap.put("ghost1", new Tile(21, 8));
        tileMap.put("ghost0", new Tile(18, 8));





        tileMap.put("cannon", new Tile(16, 2));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}

package dev.game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

    //Khởi tạo tile
    public static Tile[] tiles = new Tile[36];
    public static Tile star1Tile = new Star1Tile(0);
    public static Tile star2Tile = new Star2Tile(1);
    public static Tile star3Tile = new Star3Tile(2);
    public static Tile metroid1Tile = new Metroid1Tile(3);
    public static Tile metroid2Tile = new Metroid2Tile(4);
    public static Tile wallTile = new WallTile(5);
    //CLASS

    public static final int TILEWIDTH = 40, TILEHEIGHT = 40;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick() {
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }
}

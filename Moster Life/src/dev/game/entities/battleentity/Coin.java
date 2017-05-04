package dev.game.entities.battleentity;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.entities.ID;
import java.awt.Color;
import java.awt.Graphics;

public class Coin extends Entity {

    private int count = 0;
    public static final int DEFAULT_COIN_WIDTH = 20, DEFAULT_COIN_HEIGHT = 20;

    public Coin(Handler handler, float x, float y, ID id) {
        super(handler, x, y, DEFAULT_COIN_WIDTH, DEFAULT_COIN_HEIGHT, id);
    }

    @Override
    public void tick() {
        count++;
        if (count == 150) {
            active = false; 
        }

        Entity e = checkEntityCollisions(0f, 0f);
        if (e != null && e.getID() == ID.PlayerTroop) {
            this.setActive(false);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.decode("#F9CC7B"));
        g.fillOval((int) x, (int) y, width, height);
    }

    @Override
    public void die() {
    }

}

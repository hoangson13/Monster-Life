package dev.game.entities.battleentity;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.entities.ID;
import dev.game.entities.combattroop.PlayerTroop;
import java.awt.Color;
import java.awt.Graphics;

public class Potion extends Entity {
    
    private int count = 0;
    public static final int DEFAULT_POTION_WIDTH = 20, DEFAULT_POTION_HEIGHT = 20;
    
    public Potion(Handler handler, float x, float y, ID id) {
        super(handler, x, y, DEFAULT_POTION_WIDTH, DEFAULT_POTION_HEIGHT, id);
    }
    
    @Override
    public void tick() {
        count++;
        if (count == 150) {
            active = false; 
        }
        
        Entity e = checkEntityCollisions(0f, 0f);
        if (e != null && e.getID() == ID.PlayerTroop) {
            PlayerTroop player = (PlayerTroop) e;
            if (player.getHealth() + 100 <= player.getMaxhealth()) {
                player.setHealth(player.getHealth() + 100);
            } else player.setHealth(player.getMaxhealth());
            this.setActive(false);
        }
        
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.decode("#FA7E0A"));
        g.fillOval((int) x, (int) y, width, height);
    }
    
    @Override
    public void die() {
    }
}

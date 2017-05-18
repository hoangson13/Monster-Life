package dev.game.entities.battleentity;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.entities.ID;
import dev.game.entities.combattroop.PlayerTroop;
import dev.game.gfx.Asset;
import java.awt.Graphics;
import java.util.Random;

public class Potion extends Entity {
    
    private int count = 0, timeAppear;
    private Random r;
    public static final int DEFAULT_POTION_WIDTH = 30, DEFAULT_POTION_HEIGHT = 30;
    
    public Potion(Handler handler, float x, float y, ID id) {
        super(handler, x, y, DEFAULT_POTION_WIDTH, DEFAULT_POTION_HEIGHT, id);
        r = new Random();
		timeAppear= 61 + r.nextInt(120);
    }
    
    @Override
    public void tick() {
        count++;
        if (count >=timeAppear) {
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
    	g.drawImage(Asset.potion1, (int) x, (int) y, DEFAULT_POTION_WIDTH, DEFAULT_POTION_HEIGHT, null);
    }
    
    @Override
    public void die() {
    }
}

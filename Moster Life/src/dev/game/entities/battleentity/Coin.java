package dev.game.entities.battleentity;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.entities.ID;
import dev.game.entities.combattroop.PlayerTroop;
import dev.game.gfx.Asset;
import dev.game.item.Misc;

import java.awt.Graphics;
import java.util.Random;

public class Coin extends Entity {

    private int count = 0, timeAppear;
    private Random r;
    private Misc coin;
    private boolean type;
    public static final int DEFAULT_COIN_WIDTH = 30, DEFAULT_COIN_HEIGHT = 30;

    public Coin(Handler handler, float x, float y, ID id) {
        super(handler, x, y, DEFAULT_COIN_WIDTH, DEFAULT_COIN_HEIGHT, id);
        r = new Random();
        type = r.nextBoolean();
        timeAppear = 61 + r.nextInt(120);
        Misc.createMisc();
        if (type) {
            coin = (Misc) Misc.coin1.createNew(100);
        } else {
            coin = (Misc) Misc.coin2.createNew(200);
        }
    }

    @Override
    public void tick() {
        count++;
        if (count >= timeAppear) {
            coin.die();
            active = false;

        }

        Entity e = checkEntityCollisions(0f, 0f);
        if (e != null && e.getID() == ID.PlayerTroop) {
            PlayerTroop pt = (PlayerTroop) e;
            if (type) {
                pt.setCoin(pt.getCoin() + 100);
            } else {
                pt.setCoin(pt.getCoin() + 200);
            }
            this.setActive(false);
        }
    }

    @Override
    public void render(Graphics g) {
        if (type) {
            g.drawImage(Asset.coin1, (int) x, (int) y, DEFAULT_COIN_WIDTH, DEFAULT_COIN_HEIGHT, null);
        } else {
            g.drawImage(Asset.coin2, (int) x, (int) y, DEFAULT_COIN_WIDTH, DEFAULT_COIN_HEIGHT, null);
        }

    }

    @Override
    public void die() {
    }

}

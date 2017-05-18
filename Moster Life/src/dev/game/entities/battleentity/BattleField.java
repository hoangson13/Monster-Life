package dev.game.entities.battleentity;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.entities.ID;
import dev.game.entities.combattroop.EnemyTroop;
import dev.game.entities.combattroop.PlayerTroop;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BattleField extends Entity {

    private int count = 0, lastC, lastP, timeC, timeP;
    private Random r, rC, rP;

    public BattleField(Handler handler, ID id, PlayerTroop troop, EnemyTroop etroop) {// fix
        super(handler, 0, 0, 0, 0, id);
        handler.getGame().getEntitymanager().addEntity(etroop);
        handler.getGame().getEntitymanager().addEntity(troop);
        r = new Random();
        rC = new Random();
        rP = new Random();
        timeC = ranTimeC();
        timeP = ranTimeP();
        lastP = 0;
        lastC = 0;
    }

    @Override
    public void tick() {
        count++;
        if (count - lastC >= timeC) {
            handler.getGame().getEntitymanager().addEntity(new Coin(handler, r.nextFloat() * (handler.getHeight() - 40),
                    r.nextFloat() * (handler.getHeight() - 40), ID.Coin));
            lastC = count;
            timeC = ranTimeC();
        }
        if (count - lastP >= timeP) {
            handler.getGame().getEntitymanager().addEntity(new Potion(handler, r.nextFloat() * (handler.getHeight() - 40),
                    r.nextFloat() * (handler.getHeight() - 40), ID.Potion));
            lastP = count;
            timeP = ranTimeP();
        }
    }

    public int ranTimeC() {
        return 301 + rC.nextInt(120);
    }

    public int ranTimeP() {
        return 601 + rP.nextInt(300);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.decode("#41245C"));
        g.fillRect(0, 0, 900, 640);
        g.setColor(Color.decode("#FFE180"));
        g.fillRect(640, 0, 260, 640);
    }

    @Override
    public void die() {
    }
}

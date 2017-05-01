package dev.game.entities.ship;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.entities.EntityManager;
import dev.game.entities.ID;
import dev.game.entities.battleentity.BattleField;
import dev.game.entities.combattroop.PlayerTroop;
import dev.game.gfx.Animation;
import dev.game.gfx.Asset;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PlayerShip extends Ship {

    private Animation animDown, animUp, animLeft, animRight;
    private EntityManager entitymanager;
    private PlayerTroop troop;																// fix

    public PlayerShip(EntityManager entitymanager, Handler handler, float x, float y, ID id) {
        super(entitymanager, handler, x, y, Ship.DEFAULT_CREATURE_WIDTH, Ship.DEFAULT_CREATURE_HEIGHT, id);
        this.entitymanager = entitymanager;

        bounds.x = 5;
        bounds.y = 10;
        bounds.height = 25;
        bounds.width = 35;

        setMaxhealth(1000);
        setHealth(maxhealth);
        setAtk(50);
        setDef(0.8f);
        setSpeed(5);
        setBspeed(5);

        animDown = new Animation(500, Asset.player_down);
        animUp = new Animation(500, Asset.player_up);
        animLeft = new Animation(500, Asset.player_left);
        animRight = new Animation(500, Asset.player_right);
    }

    @Override
    public void tick() {
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

        Entity e = checkEntityCollisions(0f, 0f);
        if (e != null && e.getID() == ID.Enemy) {
            troop = new PlayerTroop(this, entitymanager, handler, 300, 500, ID.PlayerTroop);
            EnemyShip ex = (EnemyShip) e;
            entitymanager.addEntity(new BattleField(entitymanager, handler, ID.Menu, troop, ex.getEtroop()));
        }
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;
        if (handler.getKeyManager().up) {
            yMove = -speed;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
        } else {
            return animDown.getCurrentFrame();
        }
    }

    @Override
    public void die() {
    }
}

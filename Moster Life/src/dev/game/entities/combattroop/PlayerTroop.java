package dev.game.entities.combattroop;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.entities.EntityManager;
import dev.game.entities.ID;
import dev.game.entities.bullet.EnemyBullet;
import dev.game.entities.bullet.EnemySmartBullet;
import dev.game.entities.bullet.PlayerBullet;
import dev.game.entities.ship.PlayerShip;
import dev.game.gfx.Animation;
import dev.game.gfx.Asset;
import dev.game.state.EndState;
import dev.game.state.State;
import dev.game.utils.Utils;
import java.awt.Color;
import java.awt.Graphics;

public class PlayerTroop extends CombatTroop {

    private int count = 0;
    private final float pe;
    private long lastAttack, attackCooldown = 500, attackTimer = attackCooldown;
    private Animation aniplayer;
    PlayerShip player;

    public PlayerTroop(PlayerShip player, EntityManager entitymanager, Handler handler, float x, float y, ID id) {
        super(entitymanager, handler, x, y, id);
        this.player = player;
        aniplayer = new Animation(500, Asset.player_up);
        setMaxhealth(player.getMaxhealth());
        setHealth(player.getHealth());
        setAtk(player.getAtk());
        setDef(player.getDef());
        setSpeed(player.getBspeed());
        pe = 200.0f / maxhealth;
    }

    public void tick() {
        aniplayer.tick();
        x = Utils.clamp(0, 600, handler.getMouseManager().getMouseX() - 20);
        y = Utils.clamp(0, 600, handler.getMouseManager().getMouseY());

        if (handler.getMouseManager().isLeftPressed() == true) {
            attackTimer += System.currentTimeMillis() - lastAttack;
            lastAttack = System.currentTimeMillis();
            if (attackTimer > attackCooldown) {
                entitymanager.addEntity(new PlayerBullet(entitymanager, handler, x + width / 2, y - 10, ID.PlayerBullet, atk, speed));
                attackTimer = 0;
            }
        }

        Entity e = checkEntityCollisions(0f, 0f);
        if (e != null
                && (e.getID() == ID.EnemyBullet || e.getID() == ID.EnemyTroop || e.getID() == ID.SmartEnemyBullet)) {
            if (e.getID() == ID.EnemyBullet) {
                EnemyBullet bullet = (EnemyBullet) e;
                hurt((int) (bullet.getAtk() * def));
                bullet.setActive(false);
            }
            if (e.getID() == ID.SmartEnemyBullet) {
                EnemySmartBullet bullet = (EnemySmartBullet) e;
                hurt((int) (bullet.getAtk() * def));
                bullet.setActive(false);
            }
            if (e.getID() == ID.EnemyTroop) {
                EnemyTroop troop = (EnemyTroop) e;
                hurt((int) (troop.getAtk() * def));
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(aniplayer.getCurrentFrame(), (int) x, (int) y, width, height, null);
        //Player health bar        
        g.setColor(Color.decode("#FF304F"));
        g.fillRect(650, 320, 200, 20);
        g.setColor(Color.black);
        g.drawRect(650, 320, 200, 20);
        g.setColor(Color.decode("#28C7FA"));
        g.fillRect(650, 320, (int) (health * pe), 20);
        if (health <= 0) {
            g.drawString("Player's HP :" + 0, 650, 310);
        } else {
            g.drawString("Player's HP :" + health, 650, 310);
        }
    }

    @Override
    public void die() {
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            e.setActive(false);
        }
        handler.getGame().endState = new EndState(handler);
        State.setState(handler.getGame().endState);
    }
}

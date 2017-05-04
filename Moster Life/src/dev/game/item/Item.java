package dev.game.item;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.entities.ID;
import dev.game.entities.ship.PlayerShip;
import dev.game.gfx.Asset;

public class Item {

    public static Item[] items = new Item[256];
    public static Item goldChest = new Item(Asset.goldChest, "Gold Chest", 0);
    public static Item goldChest2 = new Item(Asset.goldChest, "Gold Chest2", 1);
    public static Item goldChest3 = new Item(Asset.goldChest, "Gold Chest3", 2);

    public static final int ITEMWIDTH = 40, ITEMHEIGHT = 40;
    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int itemNum;
    protected int x, y, amount;
    protected boolean active = true;
    protected Rectangle bounds;

    public Item(BufferedImage texture, String name, int itemNum) {
        this.texture = texture;
        this.name = name;
        this.itemNum = itemNum;
        amount = 100;

        items[itemNum] = this;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
    }

    public void tick() {
        if (getPlayerShip().getCollisionBounds(0f, 0f).intersects(bounds)) {
            active = false;
            getPlayerShip().getInventory().addItem(this);
        }
    }

    public void render(Graphics g) {
        if (handler == null) {
            return;
        }
        g.drawImage(texture, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), ITEMWIDTH, ITEMHEIGHT, null);
    }

    public void die() {
        active = false;
    }

    public Item createNew(int amount) {
        Item newItem = new Item(texture, name, itemNum);
        newItem.setActive(true);
        newItem.setAmount(amount);
        return newItem;
    }

    public Item createNew(int x, int y) {
        Item newItem = new Item(texture, name, itemNum);
        newItem.setPosition(x, y);
        return newItem;
    }

    public PlayerShip getPlayerShip() {
        PlayerShip ex = null;
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.getID() == ID.Player) {
                ex = (PlayerShip) e;
                break;
            }
        }
        return ex;
    }

    // GETTER SETTER
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getItemNum() {
        return itemNum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}

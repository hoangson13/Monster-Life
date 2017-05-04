package dev.game.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.game.Handler;
import dev.game.gfx.Asset;
import dev.game.gfx.Text;
import dev.game.item.Item;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;

    private int invX = 64, invY = 32, invWidth = 768, invHeight = 576, invListCenterX = invX + 256,
            invListCenterY = invY + invHeight / 2 + 7, invListSpacing = 45;

    private int invImageX = 636, invImageY = 72, invImageWidth = 116, InvImageHeight = 116;

    private int invAmountX = 694, invAmountY = 220;

    private int selectedItem = 0;

    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();

    }

    public void tick() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            active = !active;
        }
        if (!active) {
            return;
        }

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) {
            selectedItem--;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) {
            selectedItem++;
        }

        if (selectedItem < 0) {
            selectedItem = inventoryItems.size() - 1;
        } else if (selectedItem >= inventoryItems.size()) {
            selectedItem = 0;
        }

    }

    public void render(Graphics g) {
        if (!active) {
            return;
        }
        g.drawImage(Asset.inventoryScreen, invX, invY, invWidth, invHeight, null);

        int len = inventoryItems.size();
        if (len == 0) {
            return;
        }

        for (int i = -5; i < 6; i++) {
            if (selectedItem + i < 0 || selectedItem + i >= len) {
                continue;
            }

            if (i == 0) {
                Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " < ", invListCenterX,
                        invListCenterY + i * invListSpacing, true, Color.YELLOW, Asset.font36);
            } else {
                Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX,
                        invListCenterY + i * invListSpacing, true, Color.WHITE, Asset.font36);
            }
        }

        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, InvImageHeight, null);
        Text.drawString(g, Integer.toString(item.getAmount()), invAmountX, invAmountY, true, Color.decode("#835AF1"), Asset.font36);
    }

    // Methods
    public void addItem(Item item) {
        for (Item i : inventoryItems) {
            if (i.getItemNum() == item.getItemNum()) {
                i.setAmount(i.getAmount() + item.getAmount());
                return;
            }

        }
        inventoryItems.add(item);
    }

    // GETTER SETTER
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }

}

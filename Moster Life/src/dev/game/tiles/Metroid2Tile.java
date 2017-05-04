package dev.game.tiles;

import dev.game.gfx.Asset;

public class Metroid2Tile extends Tile {

    public Metroid2Tile(int id) {
        super(Asset.metroid2, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}

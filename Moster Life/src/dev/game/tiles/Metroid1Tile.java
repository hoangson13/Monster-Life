package dev.game.tiles;

import dev.game.gfx.Asset;

public class Metroid1Tile extends Tile {

    public Metroid1Tile(int id) {
        super(Asset.metroid1, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}

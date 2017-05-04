package dev.game.tiles;

import dev.game.gfx.Asset;

public class WallTile extends Tile {

    public WallTile(int id) {
        super(Asset.wall, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}

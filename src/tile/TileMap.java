package tile;

import tile.blocks.Block;

import java.awt.*;
import java.util.ArrayList;

public abstract class TileMap {
    public abstract void render(Graphics2D g2D);
    public abstract ArrayList<Block> getBlocks();
}

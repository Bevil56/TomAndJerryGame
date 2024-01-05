package tile;

import graphics.Sprite;
import utils.Vector2f;
import tile.blocks.Block;
import tile.blocks.NormBlock;

import java.awt.*;
import java.util.ArrayList;

public class TileMapNorm extends TileMap {

    private ArrayList<Block> blocks;



    public TileMapNorm(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        blocks = new ArrayList<Block>();

        String[] block = data.split(",");

        for(int j = 0; j < height; j++) {
            for(int i = 0; i < width; i++) {
                int temp = Integer.parseInt(block[j * width + i].replaceAll("\\s+", ""));
                if(temp != 0) {
                    blocks.add(new NormBlock(sprite.getSprite((temp - 1) % tileColumns, (temp - 1) / tileColumns),
                            new Vector2f(i * tileWidth, j * tileHeight), tileWidth, tileHeight));
                }
            }
        }
    }


    @Override
    public void render(Graphics2D g2D) {
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).render(g2D);
        }
    }
}

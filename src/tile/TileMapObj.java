package tile;

import graphics.Sprite;
import utils.Vector2f;
import tile.blocks.Block;
import tile.blocks.HoleBlock;
import tile.blocks.ObjBlock;

import java.awt.*;
import java.util.HashMap;

public class TileMapObj extends TileMap {

    public static HashMap<String, Block> tmo_blocks;



    public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        tmo_blocks = new HashMap<String, Block>();

        String[] block = data.split(",");

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int index = j * width + i;
                int temp = Integer.parseInt(block[index].replaceAll("\\s+", ""));

                if (temp != 0) {
                    Block tempBlock;
                    if (temp == 37 || temp == 38 || temp == 39 || temp == 45 || temp == 46 || temp == 47 || temp == 53 || temp == 54 || temp == 55) {
                        tempBlock = new HoleBlock(
                                sprite.getSprite((temp - 1) % tileColumns, (temp - 1) / tileColumns),
                                new Vector2f(i * tileWidth, j * tileHeight),
                                tileWidth,
                                tileHeight
                        );
                    } else {
                        tempBlock = new ObjBlock(
                                sprite.getSprite((temp - 1) % tileColumns, (temp - 1) / tileColumns),
                                new Vector2f(i * tileWidth, j * tileHeight),
                                tileWidth,
                                tileHeight
                        );
                    }
                    tmo_blocks.put(i + "," + j, tempBlock);
                }
            }
        }
        for (int j = 0; j < height; j++) {
            Block tempBlock1 = new ObjBlock(new Vector2f(-tileWidth, j * tileHeight), tileWidth, tileHeight);
            tmo_blocks.put(-1 + "," + j, tempBlock1);

            Block tempBlock2 = new ObjBlock(new Vector2f((width + 1) * tileWidth, j * tileHeight), tileWidth, tileHeight);
            tmo_blocks.put((width + 1) + "," + j, tempBlock2);
        }
    }


    @Override
    public void render(Graphics2D g2D) {
        for (Block block: tmo_blocks.values()){
            block.render(g2D);
        }
    }
}

package tile;

import graphics.Sprite;
import utils.Vector2f;
import tile.blocks.Block;
import tile.blocks.NormBlock;
import utils.pathfinder.Grid;

import java.awt.*;
import java.util.ArrayList;

public class TileMapNorm extends TileMap {

    private ArrayList<Block> blocks;


    protected static boolean[][] walkableTiles;
    protected static Grid grid;

    private int width;
    private int height;

    public TileMapNorm(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns, int index) {
        blocks = new ArrayList<Block>();
        this.width = width;
        this.height = height;
        String[] block = data.split(",");
        if (index == 1) {
            walkableTiles = new boolean[width][height];
            grid = new Grid(width, height, walkableTiles);
            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    int temp = Integer.parseInt(block[j * width + i].replaceAll("\\s+", ""));
                    if (temp != 0) {
                        Block normBlock = new NormBlock(sprite.getSprite((temp - 1) % tileColumns, (temp - 1) / tileColumns),
                                new Vector2f(i * tileWidth, j * tileHeight), tileWidth, tileHeight);
                        blocks.add(normBlock);
                        walkableTiles[i][j] = true;
                    }
                }
            }
        } else {
            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    int temp = Integer.parseInt(block[j * width + i].replaceAll("\\s+", ""));
                    if (temp != 0) {
                        Block normBlock = new NormBlock(sprite.getSprite((temp - 1) % tileColumns, (temp - 1) / tileColumns),
                                new Vector2f(i * tileWidth, j * tileHeight), tileWidth, tileHeight);
                        blocks.add(normBlock);
                        walkableTiles[i][j] = false;
                    }
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

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public static boolean[][] getWalkableTiles() {
        return walkableTiles;
    }

    public static Grid getGrid() {
        return grid;
    }

    public void printWalkableTiles() {
        System.out.println("Walkable tiles:");
        for (int j = 0; j < getWalkableTiles()[0].length; j++) {
            for (int i = 0; i < getWalkableTiles().length; i++) {
                System.out.print(getWalkableTiles()[i][j] ? "1 " : "0 ");
            }
            System.out.println();
        }
    }
}

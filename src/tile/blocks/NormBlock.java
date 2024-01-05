package tile.blocks;

import utils.AABB;
import utils.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormBlock extends Block {
    public NormBlock(BufferedImage img, Vector2f pos, int w, int h) {
        super(img, pos, w, h);
    }

    @Override
    public boolean update(AABB p) {
        return false;
    }

    @Override
    public boolean isInside(AABB p) {
        return false;
    }

    @Override
    public BufferedImage getImage() {
        return null;
    }
    public void render(Graphics2D g2D){
        super.render(g2D);
    }
}

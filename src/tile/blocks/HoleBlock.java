package tile.blocks;

import math.AABB;
import math.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HoleBlock extends Block {
    public HoleBlock(BufferedImage img, Vector2f pos, int w, int h) {
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
        g2D.setColor(Color.GREEN);
        g2D.drawRect((int) pos.x, (int) pos.y, w, h);
    }
}

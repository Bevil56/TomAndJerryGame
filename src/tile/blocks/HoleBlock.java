package tile.blocks;

import utils.AABB;
import utils.Vector2f;

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
        if (p.getPos().x + p.getXOffset() < pos.x) return false;
        if (p.getPos().y + p.getYOffset() < pos.y) return false;
        if (w + pos.x < p.getWidth() + (p.getPos().x + p.getXOffset())) return false;
        if (h + pos.y < p.getHeight() + (p.getPos().y + p.getYOffset())) return false;
        return true;
    }

    @Override
    public BufferedImage getImage() {
        return null;
    }

    @Override
    public boolean isWalkable(AABB p) {
        return false;
    }

    public void render(Graphics2D g2D) {
        super.render(g2D);
//        g2D.setColor(Color.GREEN);
//        g2D.drawRect((int) pos.x, (int) pos.y, w, h);
    }
}

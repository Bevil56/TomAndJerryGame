package tile.blocks;

import utils.AABB;
import utils.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjBlock extends Block {
    public ObjBlock(Vector2f pos, int w, int h) {
        super(pos, w, h);
    }

    public ObjBlock(BufferedImage img, Vector2f pos, int w, int h) {
        super(img, pos, w, h);
    }

    @Override
    public boolean update(AABB p) {
        return true;
    }

    @Override
    public boolean isInside(AABB p) {
        return false;
    }

    @Override
    public BufferedImage getImage() {
        return null;
    }

    @Override
    public boolean isWalkable(AABB p) {
        return false;
    }

    public void render(Graphics2D g2D){
        super.render(g2D);
        g2D.setColor(Color.RED);
        g2D.drawRect((int) pos.x, (int) pos.y, w, h);
    }
}

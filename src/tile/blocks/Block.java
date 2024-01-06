package tile.blocks;

import utils.AABB;
import utils.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block {
    protected int w;
    protected int h;

    public BufferedImage img;
    public Vector2f pos;

    public Block(Vector2f pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;
    }

    public Block(BufferedImage img, Vector2f pos, int w, int h) {
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
    }

    public int getWidth() { return w; }
    public int getHeight() { return h; }

    public abstract boolean update(AABB p);
    public abstract boolean isInside(AABB p);

    public abstract BufferedImage getImage();
    public Vector2f getPos() { return pos; }

    public abstract boolean isWalkable(AABB p);

    public void render(Graphics2D g) {
        g.drawImage(img, (int) pos.x, (int) pos.y, w, h, null);
    }
}


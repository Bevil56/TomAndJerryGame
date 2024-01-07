package entity;

import graphics.Sprite;
import utils.AABB;
import utils.Vector2f;

import java.awt.*;

public abstract class Item {
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;
    protected AABB bounds;

    public Item(Sprite sprite, Vector2f pos, int size) {
        this.sprite = sprite;
        this.pos = pos;
        this.size = size;
        bounds = new AABB(pos, size, size);
    }

    public AABB getBounds() {
        return bounds;
    }

    public abstract void update();

    public abstract void render(Graphics2D g2D);
}

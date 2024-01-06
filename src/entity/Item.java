package entity;

import graphics.Sprite;
import utils.Vector2f;

import java.awt.*;

public abstract class Item {
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;
    public Item(Sprite sprite, Vector2f pos, int size){
        this.sprite = sprite;
        this.pos = pos;
        this.size = size;
    }
    public abstract void update();
    public abstract void render(Graphics2D g2D);
}

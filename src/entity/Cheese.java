package entity;

import graphics.Sprite;
import utils.Vector2f;

import java.awt.*;


public class Cheese extends Item {

    private boolean eaten;

    public Cheese(Sprite sprite, Vector2f pos, int size) {
        super(sprite, pos, size);
        bounds.setWidth(14);
        bounds.setHeight(14);
        bounds.setXOffset(10);
        bounds.setYOffset(10);
        eaten = false;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2D) {
//        g2D.setColor(Color.WHITE);
//        g2D.drawRect((int) (pos.x + bounds.getXOffset()), (int) (pos.y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
        g2D.drawImage(sprite.getSprite(), (int) (pos.x), (int) (pos.y), size, size, null);
    }

    public boolean isEaten() {
        return eaten;
    }

    public void eat() {
        eaten = true;
    }
}

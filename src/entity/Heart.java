package entity;

import graphics.Sprite;
import utils.Vector2f;

import java.awt.*;

public class Heart extends Item{
    public Heart(Sprite sprite, Vector2f pos, int size) {
        super(sprite, pos, size);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2D) {
        g2D.drawImage(sprite.getSprite(), (int) (pos.x), (int) (pos.y), size, size, null);
    }
}

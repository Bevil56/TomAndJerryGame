package entity;

import game.GamePanel;
import graphics.Sprite;
import utils.AABB;
import utils.Vector2f;
import utils.KeyHandler;
import utils.MouseHandler;

import java.awt.*;

public class Tom extends Entity{

    private AABB sense;
    private int radius;

    public Tom(Sprite sprite, Vector2f vector2f, int size) {
        super(sprite, vector2f, size);
        acceleration = 1f;
        maxSpeed = 1.5f;
        deceleration = 0.1f;
        radius = 135;
        sprite.setTileSize(64);

        bounds.setWidth(25);
        bounds.setHeight(15);
        bounds.setXOffset(18);
        bounds.setYOffset(45);

        sense = new AABB(new Vector2f((vector2f.x - size / 2), (vector2f.y - size / 2)), radius);
    }
    public void move(Jerry jerry) {
        if(sense.colCirBox(jerry.getBounds())){
            if (pos.y > jerry.pos.y + 1) {
                dy -= acceleration;
                up = true;
                down = false;
                if (dy < -maxSpeed) {
                    dy = -maxSpeed;
                }
            }
            else if (pos.y < jerry.pos.y - 1) {
                dy += acceleration;
                down = true;
                up = false;
                if (dy > maxSpeed) {
                    dy = maxSpeed;
                }
            }
            else {
                dy = 0;
                up = false;
                down = false;
            }

            if (pos.x > jerry.pos.x + 1) {
                dx -= acceleration;
                left = true;
                right = false;
                if (dx < -maxSpeed) {
                    dx = -maxSpeed;
                }
            }
            else if (pos.x < jerry.pos.x - 1) {
                dx += acceleration;
                right = true;
                left = false;
                if (dx > maxSpeed) {
                    dx = maxSpeed;
                }
            }
            else {
                dx = 0;
                left = right = false;
            }

            if (up && left || up && right || down && left || down && right) {
                dy *= diagonalFactor;
                dx *= diagonalFactor;
            }
        }
        else {
            up = down = left = right =false;
            dx = 0;
            dy = 0;
        }
    }
    public void update(Jerry jerry){
        super.update();
        move(jerry);
        if (!tileCollision.collisionTile(dx, 0)) {
            if (pos.x + dx >= 0 && pos.x + dx + bounds.getWidth() * 2 <= GamePanel.width) {
                sense.getPos().x += dx;
                pos.x += dx;
            }
        }

        if (!tileCollision.collisionTile(0, dy)) {
            sense.getPos().y += dy;
            pos.y += dy;
        }
    }
    @Override
    public void render(Graphics2D g2D) {
        g2D.setColor(Color.GREEN);
        g2D.drawRect((int) (pos.x + bounds.getXOffset()), (int) (pos.y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());

        g2D.setColor(Color.BLUE);
        g2D.drawOval((int) sense.getPos().x, (int) sense.getPos().y, radius, radius);

        g2D.drawImage(animation.getImage(),(int) (pos.x), (int) (pos.y), size, size, null);
    }
    public void input(MouseHandler mouse, KeyHandler key){
//        if(mouse.getButton() == 1){
//            System.out.println("Tom: " + pos.x + " " + pos.y);
//        }
//        up = key.up.down;
//        down = key.down.down;
//        left = key.left.down;
//        right = key.right.down;
    }
}

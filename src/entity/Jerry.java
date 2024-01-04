package entity;

import graphics.Sprite;
import math.Vector2f;
import utils.KeyHandler;
import utils.MouseHandler;

import java.awt.*;

public class Jerry extends Entity{
    public Jerry(Sprite sprite, Vector2f vector2f, int size) {
        super(sprite, vector2f, size);
    }
    public void move() {
        if(up && !left && !right) {
            dy -= acceleration;
            if(dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else if(down && !left && !right) {
            dy += acceleration;
            if(dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if(dy < 0) {
                dy += deceleration;
                if(dy > 0) {
                    dy = 0;
                }
            } else if (dy > 0) {
                dy -= deceleration;
                if(dy < 0) {
                    dy = 0;
                }
            }
        }

        if(left && !up && !down) {
            dx -= acceleration;
            if(dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else if(right && !up && !down) {
            dx += acceleration;
            if(dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if(dx < 0) {
                dx += deceleration;
                if(dx > 0) {
                    dx = 0;
                }
            } else if (dx > 0) {
                dx -= deceleration;
                if(dx < 0) {
                    dx = 0;
                }
            }
        }
    }
    public void update(){
        super.update();
        move();
        pos.x += dx;
        pos.y += dy;
    }
    @Override
    public void render(Graphics2D g2D) {
        g2D.drawImage(animation.getImage(),(int) (pos.x), (int) (pos.y), size, size, null);
    }
    public void input(MouseHandler mouse, KeyHandler key){
        up = key.up.down;
        down = key.down.down;
        left = key.left.down;
        right = key.right.down;
    }
}

package entity;

import graphics.Sprite;
import math.Vector2f;
import utils.KeyHandler;
import utils.MouseHandler;

import java.awt.*;

public class Player extends Entity{
    public Player(Sprite sprite, Vector2f vector2f, int size) {
        super(sprite, vector2f, size);
    }
    public void move() {
        if(up) {
            dy -= acceleration;
            if(dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if(dy < 0) {
                dy += deceleration;
                if(dy > 0) {
                    dy = 0;
                }
            }
        }

        if(down) {
            dy += acceleration;
            if(dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if(dy > 0) {
                dy -= deceleration;
                if(dy < 0) {
                    dy = 0;
                }
            }
        }

        if(left) {
            dx -= acceleration;
            if(dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if(dx < 0) {
                dx += deceleration;
                if(dx > 0) {
                    dx = 0;
                }
            }
        }

        if(right) {
            dx += acceleration;
            if(dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if(dx > 0) {
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
        if (mouse.getButton() == 1){
            System.out.println("Player: "+ pos.x + ","+ pos.y);
        }
        up = key.up.down;
        down = key.down.down;
        left = key.left.down;
        right = key.right.down;
    }
}

package entity;

import graphics.Animation;
import graphics.Sprite;
import math.Vector2f;
import utils.KeyHandler;
import utils.MouseHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    private final int UP = 0;
    private final int DOWN = 1;
    private final int RIGHT = 2;
    private final int LEFT = 3;

    protected int currentAnimation;

    protected Animation animation;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    protected boolean up;
    protected boolean down;
    protected boolean left;
    protected boolean right;

    protected float dx;
    protected float dy;

    protected float maxSpeed;
    protected float acceleration;
    protected float deacceleration;

    public Entity(Sprite sprite, Vector2f vector2f, int size) {
        this.sprite = sprite;
        pos = vector2f;
        this.size = size;
        animation = new Animation();
        setAnimation(RIGHT,sprite.getSpriteArray(RIGHT),10);
    }

    private void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        animation.setFrames(frames);
        animation.setDelay(delay);
    }
    private void update(){
        animate();
        setHitBoxDirection();
        animation.update();
    }

    private void animate() {
        if(up){
            if (currentAnimation != UP || animation.getDelay() == -1){
                setAnimation(UP,sprite.getSpriteArray(UP),5);
            }
        }
        else if(down){
            if (currentAnimation != DOWN || animation.getDelay() == -1){
                setAnimation(DOWN,sprite.getSpriteArray(DOWN),5);
            }
        }
        else if(left){
            if (currentAnimation != LEFT || animation.getDelay() == -1){
                setAnimation(LEFT,sprite.getSpriteArray(LEFT),5);
            }
        }
        else if(right){
            if (currentAnimation != RIGHT || animation.getDelay() == -1){
                setAnimation(RIGHT,sprite.getSpriteArray(RIGHT),5);
            }
        }
        else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation),-1);
        }
    }

    private void setHitBoxDirection() {
    }

    public abstract void render(Graphics2D g2D);
    public void input(MouseHandler mouse, KeyHandler key) {
        
    }
}

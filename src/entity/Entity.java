package entity;

import graphics.Animation;
import graphics.Sprite;
import math.AABB;
import math.Vector2f;
import utils.KeyHandler;
import utils.MouseHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    private final int LEFT = 0;
    private final int RIGHT = 1;
    private final int DOWN = 2;
    private final int UP = 3;

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

    protected float maxSpeed = 2f;
    protected float acceleration = 1f;
    protected float deceleration = 0.3f;

    protected AABB hitBounds;
    protected AABB bounds;


    public Entity(Sprite sprite, Vector2f vector2f, int size) {
        this.sprite = sprite;
        pos = vector2f;
        this.size = size;

        bounds = new AABB(vector2f, size, size);
        hitBounds = new AABB(new Vector2f(vector2f.x + ((float) size / 2), vector2f.y), size, size);

        animation = new Animation();
        setAnimation(RIGHT,sprite.getSpriteArray(RIGHT),10);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
    }

    public AABB getBounds() {
        return bounds;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public void setMaxSpeed(float maxSpeed){
        this.maxSpeed = maxSpeed;
    }
    public int getSize() {
        return size;
    }
    public Animation getAnimation(){
        return animation;
    }
    private void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        animation.setFrames(frames);
        animation.setDelay(delay);
    }
    public void update(){
        animate();
        setHitBoxDirection();
        animation.update();
    }

    public void animate() {
        if(up){
            if (currentAnimation != UP || animation.getDelay() == -1){
                setAnimation(UP,sprite.getSpriteArray(UP),8);
            }
        }
        else if(down){
            if (currentAnimation != DOWN || animation.getDelay() == -1){
                setAnimation(DOWN,sprite.getSpriteArray(DOWN),8);
            }
        }
        else if(left){
            if (currentAnimation != LEFT || animation.getDelay() == -1){
                setAnimation(LEFT,sprite.getSpriteArray(LEFT),8);
            }
        }
        else if(right){
            if (currentAnimation != RIGHT || animation.getDelay() == -1){
                setAnimation(RIGHT,sprite.getSpriteArray(RIGHT),8);
            }
        }
        else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation),-1);
        }
    }

    private void setHitBoxDirection() {
        if (up){
            hitBounds.setYOffset((float) -size / 2);
            hitBounds.setXOffset((float) -size / 2);
        }
        else if (down){
            hitBounds.setYOffset((float) size / 2);
            hitBounds.setXOffset((float) -size / 2);
        }
        else if (left){
            hitBounds.setXOffset((float) -size);
            hitBounds.setYOffset(0);
        }
        else if (right){
            hitBounds.setYOffset(0);
            hitBounds.setXOffset(0);
        }
    }

    public abstract void render(Graphics2D g2D);
    public void input(MouseHandler mouse, KeyHandler key) {

    }
}

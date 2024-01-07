package utils;

import entity.Entity;

public class AABB {
    private Vector2f pos;
    private float xOffset;
    private float yOffset;
    private float width;
    private float height;
    private float radius;
    private int size;
    private Entity entity;

    public AABB(Vector2f pos, int width, int height){
        this.pos = pos;
        this.width = width;
        this.height = height;

        size  = Math.max(width, height);
    }
    public AABB(Vector2f pos, int radius){
        this.pos = pos;
        this.radius = radius;

        size = radius;
    }

    public Vector2f getPos() {
        return pos;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRadius() {
        return radius;
    }
    public void setBox(Vector2f pos, int width, int height){
        this.pos = pos;
        this.width = width;
        this.height = height;

        size = Math.max(width, height);
    }
    public void setCircle(Vector2f pos, int radius){
        this.pos = pos;
        this.radius = radius;

        size = radius;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setXOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }
    public boolean collides(AABB bBox) {
        float thisCenterX = pos.x + xOffset + width / 2;
        float thisCenterY = pos.y + yOffset + height / 2;

        float otherMinX = bBox.getPos().x + bBox.getXOffset();
        float otherMaxX = otherMinX + bBox.getWidth();
        float otherMinY = bBox.getPos().y + bBox.getYOffset();
        float otherMaxY = otherMinY + bBox.getHeight();

        float thisMinX = thisCenterX - width / 2;
        float thisMaxX = thisCenterX + width / 2;
        float thisMinY = thisCenterY - height / 2;
        float thisMaxY = thisCenterY + height / 2;

        return thisMaxX > otherMinX && thisMinX < otherMaxX &&
                thisMaxY > otherMinY && thisMinY < otherMaxY;
    }



    public boolean colCirBox(AABB aBox){
        float dx = Math.max(aBox.getPos().x + aBox.getXOffset() , Math.min(aBox.getPos().x + (radius / 2), aBox.getPos().x + aBox.getXOffset() + aBox.getWidth()));
        float dy = Math.max(aBox.getPos().y + aBox.getYOffset() , Math.min(aBox.getPos().y + (radius / 2), aBox.getPos().y + aBox.getYOffset() + aBox.getHeight()));

        dx = pos.x + (radius / 2) - dx;
        dy = pos.y + (radius / 2) - dy;

        if((Math.sqrt(dx * dx + dy * dy) < radius / 2)){
            {
                return true;
            }
        }
        return false;
    }

    public float getXOffset() {
        return xOffset;
    }

    public float getYOffset() {
        return yOffset;
    }
}
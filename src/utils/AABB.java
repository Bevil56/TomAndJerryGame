package utils;

import entity.Entity;
import game.GamePanel;
import tile.TileMapObj;
import tile.blocks.Block;
import tile.blocks.HoleBlock;

public class    AABB {
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
    public AABB(Vector2f pos, int radius, Entity entity){
        this.pos = pos;
        this.radius = radius;
        this.entity = entity;

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
    public boolean collides(AABB bBox){
        float ax = ((pos.getWorldVar().x)+(xOffset)+(width / 2));
        float ay = ((pos.getWorldVar().y)+(yOffset)+(height / 2));
        float bx = ((bBox.pos.getWorldVar().x)+(bBox.xOffset / 2)+(width / 2));
        float by = ((bBox.pos.getWorldVar().y)+(bBox.yOffset / 2)+(height / 2));

        if(Math.abs(ax - bx) < (this.width / 2) + (bBox.width / 2) ){
            if(Math.abs(ay - by) < (this.height / 2) + (bBox.height / 2) ){
                return true;
            }
        }
        return false;
    }
    public boolean colCirBox(AABB aBox){
        float cx = (float) (pos.getWorldVar().x + radius / Math.sqrt(2) - entity.getSize() / Math.sqrt(2));
        float cy = (float) (pos.getWorldVar().y + radius / Math.sqrt(2) - entity.getSize() / Math.sqrt(2));

        float xDelta = cx -  Math.max(aBox.pos.getWorldVar().x + (aBox.getWidth() / 2), Math.min(cx, aBox.pos.getWorldVar().x));
        float yDelta = cy -  Math.max(aBox.pos.getWorldVar().y + (aBox.getHeight() / 2), Math.min(cx, aBox.pos.getWorldVar().y));

        if((xDelta * xDelta + yDelta * yDelta) < ((this.radius / Math.sqrt(2) * (this.radius / Math.sqrt(2))))){
            return true;
        }
        return false;
    }
    public boolean collisionTile(float ax, float ay){
        for (int c = 0; c < 4 ; c++){
            int xt = (int) ((pos.x + ax) + (c % 2) * width + xOffset) / 32;
            if(pos.x < 0){
                xt = ((int) (((pos.x + ax) + (c % 2) * width + xOffset)) / 32) - 1;
            }
            int yt = (int) ((pos.y + ay) + (int)(c / 2) * height + yOffset) / 32;
            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                Block block = TileMapObj.tmo_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt));
                if (block instanceof HoleBlock) {
                    collisionHole(ax, ay, xt, yt, block);
                }
                return block.update(this);
            }
        }
        return false;
}

    private boolean collisionHole(float ax, float ay, float xt, float yt, Block block) {
        int nextXt = (int) (((pos.x + ax) + xOffset) / 32 + width / 32);
        int nextYt = (int) (((pos.x + ay) + yOffset) / 32 + height / 32);
        if((nextXt == yt + 1) || nextYt == xt + 1){
            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(nextXt) + "," + String.valueOf(nextYt))){
                Block nextBlock = TileMapObj.tmo_blocks.get(String.valueOf(nextXt) + "," + String.valueOf(nextYt));
                return nextBlock.update(this);
            }
        else {
            if(block.isInside(this)){}
                return block.update(this);
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
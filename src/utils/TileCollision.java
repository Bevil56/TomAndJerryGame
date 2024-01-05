package utils;

import entity.Entity;
import tile.TileMapObj;
import tile.blocks.Block;
import tile.blocks.HoleBlock;

public class TileCollision {
    private Entity entity;
    private Block block;

    public TileCollision(Entity entity){
        this.entity = entity;
    }
    public boolean collisionTile(float ax, float ay){
        for (int c = 0; c < 4 ; c++){
            int xt = (int) ((entity.getBounds().getPos().x + ax) + (c % 2) * entity.getBounds().getWidth() + entity.getBounds().getXOffset()) / 32;
            int yt = (int) ((entity.getBounds().getPos().y + ay) + (int)(c / 2) * entity.getBounds().getHeight() + entity.getBounds().getYOffset()) / 32;
            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))) {
                Block block = TileMapObj.tmo_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt));
                if (block instanceof HoleBlock) {
                    collisionHole(ax, ay, xt, yt, block);
                }
                return block.update(entity.getBounds());
            }
        }
        return false;
    }

    private boolean collisionHole(float ax, float ay, float xt, float yt, Block block) {
        int nextXt = (int) (((entity.getBounds().getPos().x + ax) + entity.getBounds().getXOffset()) / 32 + entity.getBounds().getWidth() / 32);
        int nextYt = (int) (((entity.getBounds().getPos().y + ay) + entity.getBounds().getYOffset()) / 32 + entity.getBounds().getHeight() / 32);

        if(block.isInside(entity.getBounds())){
            entity.setFallen(true);
            return false;
        }

        else if((nextXt == yt + 1) || nextYt == xt + 1){
            if(TileMapObj.tmo_blocks.containsKey(String.valueOf(nextXt) + "," + String.valueOf(nextYt))){
                if(entity.getBounds().getPos().x > block.getPos().x){
                    entity.setFallen(true);
                }
                return false;
            }
        }
        entity.setFallen(false);
        return false;
    }
}

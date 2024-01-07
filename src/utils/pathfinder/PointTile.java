package utils.pathfinder;

import entity.Entity;

public class PointTile {

    public int x;
    public int y;

    public PointTile() {
        this(0, 0);
    }

    public PointTile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PointTile(PointTile pointTile) {
        this.x = pointTile.x;
        this.y = pointTile.y;
    }
    public PointTile(Entity entity, float dx, float dy) {
        this.x = (int) ((entity.getBounds().getPos().x + dx) + entity.getBounds().getWidth() / 2 + entity.getBounds().getXOffset()) / 32;
        this.y = (int) ((entity.getBounds().getPos().y + dy) + entity.getBounds().getHeight() / 2 + entity.getBounds().getYOffset()) / 32;
    }

    @Override
    public boolean equals(Object object) {
        PointTile pointTile = (PointTile) object;

        if (pointTile.equals(null)) return false;

        return (x == pointTile.x) && (y == pointTile.y);
    }

    public boolean equals(PointTile pointTile) {
        if (pointTile.equals(null)) return false;

        return (x == pointTile.x) && (y == pointTile.y);
    }

    public PointTile set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }
    public void update(Entity entity, float dx, float dy) {
        this.x = (int) ((entity.getBounds().getPos().x + dx) + entity.getBounds().getWidth() / 2 + entity.getBounds().getXOffset()) / 32;
        this.y = (int) ((entity.getBounds().getPos().y + dy) + entity.getBounds().getHeight() / 2 + entity.getBounds().getYOffset()) / 32;
    }

    @Override
    public String toString() {
        return "PointTile = {" + x +", " + y +'}';
    }
}


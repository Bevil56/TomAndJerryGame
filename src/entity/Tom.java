package entity;

import game.GamePanel;
import graphics.Sprite;
import tile.TileManager;
import utils.AABB;
import utils.Vector2f;
import utils.KeyHandler;
import utils.MouseHandler;
import utils.pathfinder.PathFinding;
import utils.pathfinder.PointTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tom extends Entity {

    private static final long RESTING_DURATION = 1;

    private List<PointTile> path;
    private boolean isResting = false;
    private long restingStartTime;


    public Tom(Sprite sprite, Vector2f vector2f, int size) {
        super(sprite, vector2f, size);
        acceleration = 0.8f;
        maxSpeed = 1f;
        deceleration = 0.05f;
        sprite.setTileSize(64);

        bounds.setWidth(25);
        bounds.setHeight(15);
        bounds.setXOffset(18);
        bounds.setYOffset(45);
        path = new ArrayList<PointTile>();
    }

    public void move() {
        if (up) {
            dy -= acceleration;
            if (dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else if (down) {
            dy += acceleration;
            if (dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if (dy < 0) {
                dy += deceleration;
                if (dy > 0) {
                    dy = 0;
                }
            } else if (dy > 0) {
                dy -= deceleration;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }

        if (left) {
            dx -= acceleration;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else if (right) {
            dx += acceleration;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deceleration;
                if (dx > 0) {
                    dx = 0;
                }
            } else if (dx > 0) {
                dx -= deceleration;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }

        if (up && left || up && right || down && left || down && right) {
            dy *= diagonalFactor;
            dx *= diagonalFactor;
        }
    }

    public void followPath(Jerry jerry) {
        if (path != null && !path.isEmpty()) {
            PointTile targetPoint = path.get(0);
            float targetX = targetPoint.x * 32 + 16;
            float targetY = targetPoint.y * 32 + 16;
            float distanceX = targetX - (pos.x + bounds.getXOffset() + bounds.getWidth() / 2);
            float distanceY = targetY - (pos.y + bounds.getYOffset() + bounds.getHeight() / 2);

            float distance = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);

            if (distance > 1.0f) {
                float angle = (float) Math.atan2(distanceY, distanceX);

                dx = (float) (Math.cos(angle) * maxSpeed);
                dy = (float) (Math.sin(angle) * maxSpeed);

                double angleInDegrees = Math.toDegrees(angle);
                if (angleInDegrees >= -45 && angleInDegrees <= 45) {
                    right = true;
                    left = false;
                    up = false;
                    down = false;
                } else if (angleInDegrees >= 45 && angleInDegrees <= 135) {
                    down = true;
                    up = false;
                    left = false;
                    right = false;
                } else if (angleInDegrees >= -135 && angleInDegrees <= -45) {
                    up = true;
                    down = false;
                    left = false;
                    right = false;
                } else {
                    left = true;
                    right = false;
                    up = false;
                    down = false;
                }
            } else {
                path.remove(0);

            }
        } else {
            float targetX = jerry.getBounds().getPos().x;
            float targetY = jerry.getBounds().getPos().y;


            float distanceX = targetX - (pos.x + bounds.getXOffset() + bounds.getWidth() / 2);
            float distanceY = targetY - (pos.y + bounds.getYOffset() + bounds.getHeight() / 2);

            if (!(jerry.getBounds().collides(this.getBounds()))) {


                float angle = (float) Math.atan2(distanceY, distanceX);

                dx = (float) (Math.cos(angle) * maxSpeed);
                dy = (float) (Math.sin(angle) * maxSpeed);
                double angleInDegrees = Math.toDegrees(angle);
                if (angleInDegrees >= -45 && angleInDegrees <= 45) {
                    right = true;
                    left = false;
                    up = false;
                    down = false;
                } else if (angleInDegrees >= 45 && angleInDegrees <= 135) {
                    down = true;
                    up = false;
                    left = false;
                    right = false;
                } else if (angleInDegrees >= -135 && angleInDegrees <= -45) {
                    up = true;
                    down = false;
                    left = false;
                    right = false;
                } else {
                    left = true;
                    right = false;
                    up = false;
                    down = false;
                }
            } else {
                jerry.resetPosition();
                startResting();
            }
        }
    }

    public void update(Jerry jerry) {
        super.update();

        if (isResting()) {
            updateRestingState();
            return;
        }
        path = PathFinding.findPath(TileManager.getGrid(), this, jerry, this.getPoint(), jerry.getPoint(), true);

        move();
        followPath(jerry);


        if (!tileCollision.collisionTile(dx, 0)) {
            if (pos.x + dx >= 0 && pos.x + dx + bounds.getWidth() * 2 <= GamePanel.width) {
                pos.x += dx;
            }
        }

        if (!tileCollision.collisionTile(0, dy)) {
            pos.y += dy;
        }
    }

    @Override
    public void render(Graphics2D g2D) {

        g2D.setColor(Color.BLUE);
//        if (!path.isEmpty()) {
//            for (PointTile point : path) {
//                int renderX = point.x * 32;
//                int renderY = point.y * 32;
//                g2D.drawRect(renderX, renderY, 32, 32);
//            }
//        }
//        g2D.setColor(Color.GREEN);
//        g2D.drawRect((int) (pos.x + bounds.getXOffset()), (int) (pos.y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());

        g2D.drawImage(animation.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
    }

    public boolean isResting() {
        return isResting;
    }

    public void updateRestingState() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - restingStartTime >= RESTING_DURATION * 1000) {
            stopResting();
        }
    }

    public void startResting() {
        isResting = true;
        restingStartTime = System.currentTimeMillis();
        path.clear();
        dx = 0;
        dy = 0;
        pos.x = 20;
        pos.y = 600;
        up = down = left = right = false;
        setAnimation(DOWN, sprite.getSpriteArray(DOWN), 12);
    }

    public void stopResting() {
        isResting = false;
    }
}

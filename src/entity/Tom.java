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
import java.util.List;

public class Tom extends Entity {

    private AABB sense;
    private int radius;

    public Tom(Sprite sprite, Vector2f vector2f, int size) {
        super(sprite, vector2f, size);
        acceleration = 1f;
        maxSpeed = 1.5f;
        deceleration = 0.1f;
        radius = 300;
        sprite.setTileSize(64);

        bounds.setWidth(25);
        bounds.setHeight(15);
        bounds.setXOffset(18);
        bounds.setYOffset(45);

        sense = new AABB(new Vector2f(vector2f.x + size / 2 - radius / 2, vector2f.y + size / 2 - radius / 2), radius);
    }

    public void move() {
//        if (pos.y > jerry.pos.y + 1) {
//            dy -= acceleration;
//            up = true;
//            down = false;
//            if (dy < -maxSpeed) {
//                dy = -maxSpeed;
//            }
//        } else if (pos.y < jerry.pos.y - 1) {
//            dy += acceleration;
//            down = true;
//            up = false;
//            if (dy > maxSpeed) {
//                dy = maxSpeed;
//            }
//        } else {
//            dy = 0;
//            up = false;
//            down = false;
//        }
//
//        if (pos.x > jerry.pos.x + 1) {
//            dx -= acceleration;
//            left = true;
//            right = false;
//            if (dx < -maxSpeed) {
//                dx = -maxSpeed;
//            }
//        } else if (pos.x < jerry.pos.x - 1) {
//            dx += acceleration;
//            right = true;
//            left = false;
//            if (dx > maxSpeed) {
//                dx = maxSpeed;
//            }
//        } else {
//            dx = 0;
//            left = right = false;
//
//        }
//
//        if (up && left || up && right || down && left || down && right) {
//            dy *= diagonalFactor;
//            dx *= diagonalFactor;
//        }
//    }
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

    public void update(Jerry jerry) {
        super.update();
        move();
        List<PointTile> path = PathFinding.findPath(TileManager.getGrid(),this,jerry,this.getPoint(),jerry.getPoint(),true);
        if(path.isEmpty()){
            System.out.println("Empty path!");
        }
        else for (PointTile point : path) System.out.println(point);
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
        g2D.setColor(Color.GREEN);
        g2D.drawRect((int) (pos.x + bounds.getXOffset()), (int) (pos.y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());

        g2D.setColor(Color.BLUE);
        g2D.drawOval((int) sense.getPos().x, (int) sense.getPos().y, radius, radius);

        g2D.drawImage(animation.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
    }
}

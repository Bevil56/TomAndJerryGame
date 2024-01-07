package entity;

import game.GamePanel;
import graphics.Sprite;
import utils.CheeseGenerator;
import utils.Vector2f;
import utils.KeyHandler;
import utils.MouseHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Jerry extends Entity {
    private int score;

    public Jerry(Sprite sprite, Vector2f vector2f, int size) {
        super(sprite, vector2f, size);
        bounds.setWidth(10);
        bounds.setHeight(10);
        bounds.setXOffset(10);
        bounds.setYOffset(24);
        score = 0;
    }

    private void move() {
//        if(up && !left && !right) {
//            dy -= acceleration;
//            if(dy < -maxSpeed) {
//                dy = -maxSpeed;
//            }
//        } else if(down && !left && !right) {
//            dy += acceleration;
//            if(dy > maxSpeed) {
//                dy = maxSpeed;
//            }
//        } else {
//            if(dy < 0) {
//                dy += deceleration;
//                if(dy > 0) {
//                    dy = 0;
//                }
//            } else if (dy > 0) {
//                dy -= deceleration;
//                if(dy < 0) {
//                    dy = 0;
//                }
//            }
//        }
//
//        if(left && !up && !down) {
//            dx -= acceleration;
//            if(dx < -maxSpeed) {
//                dx = -maxSpeed;
//            }
//        } else if(right && !up && !down) {
//            dx += acceleration;
//            if(dx > maxSpeed) {
//                dx = maxSpeed;
//            }
//        } else {
//            if(dx < 0) {
//                dx += deceleration;
//                if(dx > 0) {
//                    dx = 0;
//                }
//            } else if (dx > 0) {
//                dx -= deceleration;
//                if(dx < 0) {
//                    dx = 0;
//                }
//            }
//        }
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

    public void update(List<Cheese> cheeseList) {
        super.update();
        if (!fallen) {
            move();
            checkJerryCheeseCollision(cheeseList);
            if (!tileCollision.collisionTile(dx, 0)) {
                if (pos.x + dx >= 0 && pos.x + dx + bounds.getWidth() * 2 <= GamePanel.width) {
                    pos.x += dx;
                }
            }

            if (!tileCollision.collisionTile(0, dy)) {
                pos.y += dy;
            }
        } else {
            if (animation.hasPlayedOnce()) {
                resetPosition();
                fallen = false;
            }
        }
    }

    public void resetPosition() {
        System.out.println("Reseting Player...");
        pos.x = 20;
        pos.y = 50;

        setAnimation(DOWN, sprite.getSpriteArray(DOWN), 12);
    }

    public void checkJerryCheeseCollision(List<Cheese> cheeseList) {
        for (Cheese cheese : cheeseList) {
            if (this.getBounds().collides(cheese.getBounds()) && !cheese.isEaten()) {
                cheese.eat();
                this.increaseScore();
            }
        }
    }

    public void increaseScore() {
        score += 1;
    }

    @Override
    public void render(Graphics2D g2D) {
        g2D.setColor(Color.BLUE);
        g2D.drawRect((int) (pos.x + bounds.getXOffset()), (int) (pos.y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
        g2D.drawImage(animation.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        if (!fallen) {
            up = key.up.down;
            down = key.down.down;
            left = key.left.down;
            right = key.right.down;
        } else {
            up = down = left = right = false;
        }
    }

    public int getScore() {
        return score;
    }
}

package states;

import entity.Jerry;
import entity.Tom;
import game.GamePanel;
import graphics.Sprite;
import math.Vector2f;
import utils.KeyHandler;
import utils.MouseHandler;

import graphics.Font;

import java.awt.*;

public class PlayState extends GameState {
    private Font font;
    private Jerry jerry;
    private Tom tom;

    public PlayState(GameStateManager stateManager) {
        super(stateManager);
        font = new Font("font/font.png",10,10);
        jerry = new Jerry(new Sprite("entity/jerry_animation_4.png"), new Vector2f(300,300), 32);
        tom = new Tom(new Sprite("entity/tom_animation_3.png"), new Vector2f(400,400), 64);
    }

    @Override
    public void update() {
        tom.update();
        jerry.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        jerry.input(mouse, key);
        tom.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g2D) {
        Sprite.drawSprite(g2D, font, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.width - 80,10),16,16,10,0);
        jerry.render(g2D);
        tom.render(g2D);
    }
}

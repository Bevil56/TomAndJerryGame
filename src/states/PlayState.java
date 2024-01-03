package states;

import entity.Jerry;
import entity.Tom;
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
        font = new Font("font/ZeldaFont.png",16,16);
        jerry = new Jerry(new Sprite("entity/jerry_animation.png"), new Vector2f(300,300), 32);
        tom = new Tom(new Sprite("entity/tom_animation.png"), new Vector2f(400,400), 64);
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
        Sprite.drawSprite(g2D, font, "Vinh", new Vector2f(100,100),32,32,16,0);
        jerry.render(g2D);
        tom.render(g2D);
    }
}

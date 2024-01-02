package state;

import graphics.Sprite;
import math.Vector2f;
import utils.KeyHandler;
import utils.MouseHandler;

import graphics.Font;

import java.awt.*;

public class PlayState extends GameState {
    private Font font;

    public PlayState(GameStateManager stateManager) {
        super(stateManager);
        font = new Font("font/ZeldaFont.png",16,16);
    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    @Override
    public void render(Graphics2D g2D) {
        Sprite.drawSprite(g2D, font, "Vinh", new Vector2f(100,100),32,32,16,0);
    }
}

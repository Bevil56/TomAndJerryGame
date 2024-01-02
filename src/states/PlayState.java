package states;

import entity.Player;
import graphics.Sprite;
import math.Vector2f;
import utils.KeyHandler;
import utils.MouseHandler;

import graphics.Font;

import java.awt.*;

public class PlayState extends GameState {
    private Font font;
    private Player player;

    public PlayState(GameStateManager stateManager) {
        super(stateManager);
        font = new Font("font/ZeldaFont.png",16,16);
        player = new Player(new Sprite("entity/linkformatted.png"), new Vector2f(300,300), 128);
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g2D) {
        Sprite.drawSprite(g2D, font, "Vinh", new Vector2f(100,100),32,32,16,0);
        player.render(g2D);
    }
}

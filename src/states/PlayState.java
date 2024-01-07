package states;

import entity.Cheese;
import entity.Jerry;
import entity.Tom;
import game.GamePanel;
import graphics.Sprite;
import tile.TileMapNorm;
import utils.Vector2f;
import tile.TileManager;
import utils.KeyHandler;
import utils.MouseHandler;

import graphics.Font;

import java.awt.*;

public class PlayState extends GameState {
    private Font font;
    private Jerry jerry;
    private Tom tom;
    private TileManager tm;
    private Cheese cheese;
    private TileMapNorm tmn;

    public PlayState(GameStateManager stateManager) {
        super(stateManager);

        tm = new TileManager("tile/map.xml");

        font = new Font("font/font.png",10,10);

        jerry = new Jerry(new Sprite("entity/jerry_animation_5.png"), new Vector2f(20,50), 32);
        tom = new Tom(new Sprite("entity/tom_animation_3.png"), new Vector2f(20,600), 64);
        cheese = new Cheese(new Sprite("item/cheese.png"), new Vector2f( 30,100 ), 32);
    }

    @Override
    public void update() {
        tom.update(jerry);
        jerry.update();
        cheese.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        jerry.input(mouse, key);
//        tom.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g2D) {
        tm.render(g2D);
        Sprite.drawArray(g2D, font, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.width - 100,10),16,10);
        Sprite.drawArray(g2D, font, "Score: ", new Vector2f(10,10),20,15);
        jerry.render(g2D);
        tom.render(g2D);
        cheese.render(g2D);
    }
}

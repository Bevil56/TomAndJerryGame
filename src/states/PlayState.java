package states;

import entity.Cheese;
import entity.Jerry;
import entity.Tom;
import game.GamePanel;
import graphics.Sprite;
import utils.CheeseGenerator;
import utils.Vector2f;
import tile.TileManager;
import utils.KeyHandler;
import utils.MouseHandler;

import graphics.Font;

import java.awt.*;
import java.util.List;

public class PlayState extends GameState {
    private Font font;
    private Jerry jerry;
    private Tom tom;
    private TileManager tm;
    private List<Cheese> cheeseList;

    public static Vector2f map;


    public PlayState(GameStateManager stateManager) {
        super(stateManager);

        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);

        tm = new TileManager("tile/map.xml");

        jerry = new Jerry(new Sprite("entity/jerry_animation_5.png"), new Vector2f(20, 50), 32);
        tom = new Tom(new Sprite("entity/tom_animation_3.png"), new Vector2f(20, 600), 64);
        cheeseList = CheeseGenerator.generateCheese(10);
        ;
    }

    @Override
    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        tom.update(jerry);
        jerry.update(cheeseList);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        jerry.input(mouse, key);
//        tom.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g2D) {
        tm.render(g2D);
        Sprite.drawArray(g2D, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.width - 100, 10), 16, 10);
        Sprite.drawArray(g2D, "Score: " + jerry.getScore(), new Vector2f(10, 10), 24, 15);
        jerry.render(g2D);
        tom.render(g2D);
        CheeseGenerator.renderCheese(cheeseList, g2D);
    }
}

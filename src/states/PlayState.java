package states;

import audio.Audio;
import entity.Cheese;
import entity.Heart;
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
import java.util.ArrayList;
import java.util.List;

public class PlayState extends GameState {
    private Jerry jerry;
    private Tom tom;
    private TileManager tm;
    private List<Heart> hearts;
    private List<Cheese> cheeseList;

    public static Vector2f map;


    public PlayState(GameStateManager stateManager) {
        super(stateManager);

        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);

        tm = new TileManager("tile/map.xml");
        hearts = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            hearts.add(new Heart(new Sprite("ui/hearts.png"), new Vector2f(GamePanel.width - 120 + i * 32 + 10, 10), 32));
        }
        jerry = new Jerry(new Sprite("entity/jerry_animation_5.png"), new Vector2f(20, 50), 32);
        tom = new Tom(new Sprite("entity/tom_animation_4.png"), new Vector2f(20, 600), 64);
        cheeseList = CheeseGenerator.generateCheese(4);
    }
    @Override
    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        if (!stateManager.getState(GameStateManager.PAUSE)) {
            if(jerry.isDead()) {
                stateManager.add(GameStateManager.GAME_OVER);
                stateManager.pop(GameStateManager.PLAY);
            }
            else if(cheeseList.isEmpty()) {
                stateManager.add(GameStateManager.WIN);
                stateManager.pop(GameStateManager.PLAY);
            }
            tom.update(cheeseList,jerry);
            jerry.update(cheeseList,tom);
        }
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        key.escape.tick();
        jerry.input(mouse, key);
//        tom.input(mouse, key);
        if (key.escape.clicked) {
            if (stateManager.getState(GameStateManager.PAUSE)) {
                stateManager.pop(GameStateManager.PAUSE);
            }
            else {
                stateManager.add(GameStateManager.PAUSE);
            }
        }
    }

    @Override
    public void render(Graphics2D g2D) {
        tm.render(g2D);
//        Sprite.drawArray(g2D, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.width - 100, 10), 16, 10);
        Sprite.drawArray(g2D, "Score: " + jerry.getScore(), new Vector2f(10, 10), 32, 21);
        jerry.render(g2D);
        tom.render(g2D);
        for(int i = 0; i < jerry.getLives(); i++){
            hearts.get(i).render(g2D);
        }
        CheeseGenerator.renderCheese(cheeseList, g2D);
    }
}

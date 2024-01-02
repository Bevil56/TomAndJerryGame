package states;

import game.GamePanel;
import math.Vector2f;
import utils.KeyHandler;
import utils.MouseHandler;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> states;
    private static Vector2f map;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;


    public GameStateManager() {
        map = new Vector2f(GamePanel.width, GamePanel.height);
        Vector2f.setWorldVar(map.x, map.y);
        states = new ArrayList<GameState>();
        states.add(new PlayState(this));

    }
    public void add(int state) {
        if (state == PLAY) {
            states.add(new PlayState(this));
        }
        else if (state == MENU) {
            states.add(new MenuState(this));
        }
        else if (state == PAUSE) {
            states.add(new PauseState(this));
        }
        else if (state == GAME_OVER) {
            states.add(new GameOverState(this));
        }
    }
    public void addAndpop(int state) {
        states.remove(0);
        add(state);
    }
    public void pop(int state){
        states.remove(state);
    }
    public void update(){
        Vector2f.setWorldVar(map.x, map.y);
        for (GameState state : states) {
            state.update();
        }
    };
    public void input(MouseHandler mouse, KeyHandler key){
        for (GameState state : states) {
            state.input(mouse, key);
        }
    };
    public void render(Graphics2D g2D){
        for (GameState state : states) {
            state.render(g2D);
        }
    };
}

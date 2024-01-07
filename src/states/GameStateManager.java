package states;

import graphics.Sprite;
import utils.Vector2f;
import utils.KeyHandler;
import utils.MouseHandler;
import graphics.Font;
import java.awt.*;

public class GameStateManager {

    private GameState[] states;
    private static Vector2f map;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;

    public int onTopState = 0;

    public static Font font;

    public GameStateManager() {
        states = new GameState[4];

        font = new Font("font/font.png", 10,10);
        Sprite.currentFont = font;
        states[PLAY] = new PlayState(this);
    }
    public void add(int state) {
        if(states[state] != null){
            return;}
        if (state == PLAY) {
            states[PLAY] = new PlayState(this);
        }
        else if (state == MENU) {
            states[MENU] = new MenuState(this);
        }
        else if (state == PAUSE) {
            states[PAUSE] = new PauseState(this);
        }
        else if (state == GAME_OVER) {
            states[GAME_OVER] = new GameOverState(this);

        }
    }
    public void addAndpop(int state) {
        addAndpop(state, 0);
    }
    public void addAndpop(int state, int remove) {
        pop(state);
        add(state);
    }
    public void pop(int state){
        states[state] = null;
    }
    public void update(){
        for (int i = 0; i < states.length; i++) {
            if(states[i] != null){
                states[i].update();
            }
        }
    };
    public void input(MouseHandler mouse, KeyHandler key){
        key.escape.tick();
        for (int i = 0; i < states.length; i++) {
            if(states[i] != null){
                states[i].input(mouse, key);
            }
        }
        if(key.escape.clicked){
            System.exit(0);
        }
    };
    public void render(Graphics2D g2D){
        for (int i = 0; i < states.length; i++) {
            if(states[i] != null){
                states[i].render(g2D);
            }
        }
    };
}

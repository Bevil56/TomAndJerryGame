package states;

import audio.Audio;
import graphics.Sprite;
import utils.Vector2f;
import utils.KeyHandler;
import utils.MouseHandler;
import graphics.Font;
import graphics.Fontf;

import java.awt.*;

public class GameStateManager {

    private GameState[] states;
    private static Vector2f map;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int GAME_OVER = 2;
    public static final int WIN = 3;
    public static final int PAUSE = 4;


    public static Font font;
    public static Fontf fontf;
    public static Sprite button;
    public static Graphics2D g2D;
    public static Audio audio;

    public GameStateManager(Graphics2D g2D, Audio audio) {
        GameStateManager.g2D = g2D;
        GameStateManager.audio = audio;
        states = new GameState[5];

        font = new Font("font/font.png", 10, 10);
        fontf = new Fontf();
        fontf.loadFont("font/Stackedpixel.ttf", "MeatMadness");
        Sprite.currentFont = font;

        button = new Sprite("ui/buttons.png", 64, 32);

        states[MENU] = new MenuState(this);
        GameStateManager.audio.playSound(MENU);
    }

    public void add(int state) {
        if (states[state] != null) {
            return;
        }
        if (state == PLAY) {
            states[PLAY] = new PlayState(this);
            GameStateManager.audio.playSound(PLAY);
        } else if (state == MENU) {
            states[MENU] = new MenuState(this);
            GameStateManager.audio.playSound(MENU);
        } else if (state == PAUSE) {
            states[PAUSE] = new PauseState(this);
        } else if (state == GAME_OVER) {
            states[GAME_OVER] = new GameOverState(this);
            GameStateManager.audio.playSound(GAME_OVER);
        }
        else if (state == WIN) {
            states[WIN] = new WinState(this);
            GameStateManager.audio.playSound(WIN);
        }
    }

    public boolean getState(int state) {
        return states[state] != null;
    }

    public void addAndpop(int state) {
        addAndpop(state, 1);
    }

    public void addAndpop(int state, int remove) {
        pop(state);
        add(state);
    }

    public void pop(int state) {
        states[state] = null;
    }

    public void update() {
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].update();
            }
        }
    }

    ;

    public void input(MouseHandler mouse, KeyHandler key) {
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].input(mouse, key);
            }
        }
    }

    ;

    public void render(Graphics2D g2D) {
        g2D.setFont(GameStateManager.fontf.getFont("font"));
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].render(g2D);
            }
        }
    }
    ;
}

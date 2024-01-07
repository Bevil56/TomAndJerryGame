package states;

import game.GamePanel;
import graphics.Sprite;
import utils.KeyHandler;
import utils.MouseHandler;
import utils.Vector2f;
import ui.Button;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class GameOverState extends GameState {
    private String gameover = "GAME OVER!";

    private boolean showGameOver = true;
    private boolean showButtons = false;

    private Timer timer;

    private BufferedImage imgButton;
    private BufferedImage imgHover;
    private Button btnReset;
    private Button btnQuit;
    private Font font;

    private final int COUNT_DOWN = 3;

    public GameOverState(GameStateManager gsm) {
        super(gsm);

        imgButton = GameStateManager.button.getSubimage(0, 0, 121, 26);
        imgHover = GameStateManager.button.getSubimage(0, 29, 122, 28);

        font = new Font("MeatMadness", Font.PLAIN, 48);
        btnReset = new Button("RESTART", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 - 48), 32, 16);
        btnQuit = new Button("QUIT", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 + 48), 32, 16);

        btnReset.addHoverImage(btnReset.createButton("RESTART", imgHover, font, btnReset.getWidth(), btnReset.getHeight(), 32, 20));
        btnQuit.addHoverImage(btnQuit.createButton("QUIT", imgHover, font, btnQuit.getWidth(), btnQuit.getHeight(), 32, 20));

        btnReset.addEvent(e -> {
            gsm.add(GameStateManager.PLAY);
            gsm.pop(GameStateManager.GAME_OVER);
        });

        btnQuit.addEvent(e -> {
            System.exit(0);
        });
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                showGameOver = false;
                showButtons = true;
            }
        }, COUNT_DOWN * 1000);
    }

    @Override
    public void update() {
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        key.escape.tick();

        btnReset.input(mouse, key);
        btnQuit.input(mouse, key);

        if (key.escape.clicked) {
            System.exit(0);
        }
    }

    @Override
    public void render(Graphics2D g2D) {
        if(showGameOver) {
            Sprite.drawArray(g2D, gameover, new Vector2f(GamePanel.width / 2 - gameover.length() * (64 / 2), GamePanel.height / 2 - 64 / 2), 64, 64, 64);
        }

        if(showButtons) {
            btnReset.render(g2D);
            btnQuit.render(g2D);
        }
    }
}

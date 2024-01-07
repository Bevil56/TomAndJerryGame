package states;

import game.GamePanel;

import ui.Button;
import utils.KeyHandler;
import utils.MouseHandler;
import utils.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuState extends GameState {

    private BufferedImage imgButton;
    private BufferedImage imgHover;
    private ui.Button btnStart;
    private ui.Button btnQuit;
    private Font font;
    private GameStateManager stateManager;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        this.stateManager = gsm;

        imgButton = GameStateManager.button.getSubimage(0, 0, 121, 26);
        imgHover = GameStateManager.button.getSubimage(0, 29, 122, 28);

        font = new Font("MeatMadness", Font.PLAIN, 48);
        btnStart = new ui.Button("START", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 - 48), 32, 16);
        btnQuit = new Button("QUIT", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 + 48), 32, 16);

        btnStart.addHoverImage(btnStart.createButton("START", imgHover, font, btnStart.getWidth(), btnStart.getHeight(), 32, 20));
        btnQuit.addHoverImage(btnQuit.createButton("QUIT", imgHover, font, btnQuit.getWidth(), btnQuit.getHeight(), 32, 20));

        btnStart.addEvent(e -> {
            gsm.add(GameStateManager.PLAY);
            gsm.pop(GameStateManager.MENU);
        });

        btnQuit.addEvent(e -> {
            System.exit(0);
        });
    }

    @Override
    public void update() {
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        key.escape.tick();

        btnStart.input(mouse, key);
        btnQuit.input(mouse, key);

        if (key.escape.clicked) {
            System.exit(0);
        }
    }

    @Override
    public void render(Graphics2D g2D) {
        btnStart.render(g2D);
        btnQuit.render(g2D);
    }
}


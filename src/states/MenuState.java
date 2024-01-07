package states;

import game.GamePanel;

import graphics.Sprite;
import ui.Button;
import utils.KeyHandler;
import utils.MouseHandler;
import utils.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class MenuState extends GameState {

    private BufferedImage imgButton;
    private BufferedImage imgHover;
    private BufferedImage backgroundImage;
    private ui.Button btnStart;
    private ui.Button btnQuit;
    private Font font;

    private static Sprite background;
    private static Sprite backgroundObject;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        this.stateManager = gsm;
        background = new Sprite("ui/background.png");
        backgroundObject = new Sprite("ui/background_1.png");

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
        g2D.drawImage(background.getSpriteSheet(), 0, 0, GamePanel.width, GamePanel.height, null);
        g2D.drawImage(backgroundObject.getSpriteSheet(), 50, 300, backgroundObject.getSpriteSheet().getWidth() * 2, backgroundObject.getSpriteSheet().getHeight() * 2, null);
        Sprite.drawArray(g2D, "Tom And Jerry" , new Vector2f((float) GamePanel.width / 2 - 128 * 4, 50), 128, 75);
        btnStart.render(g2D);
        btnQuit.render(g2D);
    }
}

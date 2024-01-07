package states;

import game.GamePanel;
import utils.KeyHandler;
import utils.MouseHandler;
import ui.Button;
import utils.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PauseState extends GameState {
    private Button btnResume;
    private Button btnExit;
    private Font font;

    public PauseState(GameStateManager stateManager) {
        super(stateManager);

        BufferedImage imgButton = GameStateManager.button.getSubimage(0, 0, 121, 26);
        BufferedImage imgHover = GameStateManager.button.getSubimage(0, 29, 122, 28);

        font = new Font("MeatMadness", Font.PLAIN, 48);
        btnResume = new Button("RESTART", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 - 48), 32, 16);
        btnExit = new Button("QUIT", imgButton, font, new Vector2f(GamePanel.width / 2, GamePanel.height / 2 + 48), 32, 16);

        btnResume.addHoverImage(btnResume.createButton("RESTART", imgHover, font, btnResume.getWidth(), btnResume.getHeight(), 32, 20));
        btnExit.addHoverImage(btnExit.createButton("QUIT", imgHover, font, btnExit.getWidth(), btnExit.getHeight(), 32, 20));

        btnResume.addEvent(e -> {
            stateManager.pop(GameStateManager.PAUSE);
        });

        btnExit.addEvent(e -> {
            System.exit(0);
        });
    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        btnResume.input(mouse, key);
        btnExit.input(mouse, key);

    }

    @Override
    public void render(Graphics2D g) {
        btnResume.render(g);
        btnExit.render(g);
    }
}

package state;

import utils.KeyHandler;
import utils.MouseHandler;

import java.awt.*;

public abstract class GameState {
    private GameStateManager stateManager;
    public GameState(GameStateManager stateManager) {
        this.stateManager = stateManager;
    }
    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g2D);

}

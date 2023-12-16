package state;

import util.KeyHandler;
import util.MouseHandler;

import java.awt.*;

public class PlayState extends GameState {

    public PlayState(GameStateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    @Override
    public void render(Graphics2D g2D) {
        g2D.setColor(Color.BLUE);
        g2D.fillRect(100,200,150,150);
    }
}

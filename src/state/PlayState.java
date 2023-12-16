package state;

import utils.KeyHandler;
import utils.MouseHandler;

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
        if(key.up.down){
            System.out.println("'W' is pressed");
        }
    }

    @Override
    public void render(Graphics2D g2D) {
        g2D.setColor(Color.BLUE);
        g2D.fillRect(50,50,64,64);
    }
}

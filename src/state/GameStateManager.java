package state;

import util.KeyHandler;
import util.MouseHandler;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {
    private ArrayList<GameState> states;
    public GameStateManager() {
        states = new ArrayList<GameState>();
        states.add(new PlayState(this));
    }
    public void update(){
        for(int i = 0; i < states.size(); i++){
            states.get(i).update();
        }
    };
    public void input(MouseHandler mouse, KeyHandler key){
        for(int i = 0; i < states.size(); i++){
            states.get(i).input(mouse, key);
        }
    };
    public void render(Graphics2D g2D){
        for(int i = 0; i < states.size(); i++){
            states.get(i).render(g2D);
        }
    };
}

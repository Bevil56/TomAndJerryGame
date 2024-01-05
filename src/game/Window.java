package game;

import javax.swing.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame {
    public static final long serialVersionUID = 1L;

    private BufferStrategy bs;
    private GamePanel gp;

    public Window() {
        setTitle("Tom And Jerry");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void addNotify() {
        super.addNotify();

        createBufferStrategy(3);
        bs = getBufferStrategy();

        gp = new GamePanel(bs, 1280, 704);
        setContentPane(gp);
        
    }

}

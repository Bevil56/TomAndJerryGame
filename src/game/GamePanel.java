package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class GamePanel extends JPanel implements Runnable {

    public static final long serialVersionUID = 1L;

    public static int width;
    public static int height;
    public static int oldFrameCount;
    public static int oldTickCount;
    public static int tickCount;

    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private BufferedImage img;
    private Graphics2D g;

//    private MouseHandler mouse;
//    private KeyHandler key;
//
//    private GameStateManager gsm;

    public GamePanel(BufferStrategy bs, int width, int height) {
        GamePanel.width = width;
        GamePanel.height = height;
        this.bs = bs;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void initGraphics() {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void init() {
        running = true;

        initGraphics();

//        mouse = new MouseHandler(this);
//        key = new KeyHandler(this);
//
//        gsm = new GameStateManager(g);
    }

    public void run() {
        init();

        final double GAME_HERTZ = 64.0;
        final double TIME_BEFORE_UPDATE = 1000000000 / GAME_HERTZ;
        final int MUST_UPDATE_BEFORE_RENDER = 3;

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 1000;
        final double TOTAL_TIME_BEFORE_RENDER = 1000000000 / TARGET_FPS;

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        int oldFrameCount = 0;

        int tickCount = 0;
        int oldTickCount = 0;

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;
            while (((now - lastUpdateTime) > TIME_BEFORE_UPDATE) && (updateCount < MUST_UPDATE_BEFORE_RENDER)) {
                update(now);
//                input(mouse, key);
                lastUpdateTime += TIME_BEFORE_UPDATE;
                updateCount++;
                tickCount++;
            }

            if ((now - lastUpdateTime) > TIME_BEFORE_UPDATE) {
                lastUpdateTime = now - TIME_BEFORE_UPDATE;
            }

//            input(mouse, key);

            render();
            draw();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                if (frameCount != oldFrameCount || tickCount != oldTickCount) {
                    System.out.printf("FPS: %d UPS: %d\n", frameCount, tickCount);
                    oldFrameCount = frameCount;
                    oldTickCount = tickCount;
                }

                tickCount = 0;
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while (now - lastRenderTime < TOTAL_TIME_BEFORE_RENDER && now - lastUpdateTime < TIME_BEFORE_UPDATE) {
                Thread.yield();
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.out.println("ERROR: yielding thread");
                }
                now = System.nanoTime();
            }
        }

    }

        public void update(double time) {
//        gsm.update(time);
    }

//    public void input(MouseHandler mouse, KeyHandler key) {
//        gsm.input(mouse, key);
//    }

    public void render() {
        if (g != null) {
            g.setColor(new Color(33, 30, 39));
            g.fillRect(0, 0, width, height);
//            gsm.render(g);
        }
    }

    public void draw() {
        do {
            Graphics g2 = (Graphics) bs.getDrawGraphics();
            g2.drawImage(img, 3, 26, width + 10, height + 10, null); // true 8, 31
            g2.dispose();
            bs.show();
        } while(bs.contentsLost());
        
    }
}

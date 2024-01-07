package graphics;

import java.awt.*;
import java.awt.Font;
import java.util.HashMap;

public class Fontf {

    private HashMap<String, java.awt.Font> fonts;

    public Fontf() {
        fonts = new HashMap<String, java.awt.Font>();
    }

    public void loadFont(String path, String name) {
        try {
            System.out.println("Loading: " + path + "...");
            java.awt.Font customFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream(path));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            java.awt.Font font = new java.awt.Font(name, java.awt.Font.PLAIN, 32);

            fonts.put(name, font);
        } catch (Exception e) {
            System.out.println("ERROR: ttfFont - can't load font " + path + "...");
            e.printStackTrace();
        }
    }

    public Font getFont(String name) {
        return fonts.get(name);
    }

}

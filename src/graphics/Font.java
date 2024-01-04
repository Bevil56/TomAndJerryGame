package graphics;

import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.imageio.ImageIO;

public class Font {

    private BufferedImage FONTSHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int width;
    public int heigth;
    private int letterWidth;
    private int letterHeight;

    public Font(String file) {
        width = TILE_SIZE;
        heigth = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        FONTSHEET = loadFont(file);

        letterWidth = FONTSHEET.getWidth() / width;
        letterHeight = FONTSHEET.getHeight() / heigth;
        loadFontArray();
    }

    public Font(String file, int w, int h) {
        this.width = w;
        this.heigth = h;

        System.out.println("Loading: " + file + "...");
        FONTSHEET = loadFont(file);

        letterWidth = FONTSHEET.getWidth() / w;
        letterHeight = FONTSHEET.getHeight() / h;
        loadFontArray();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        width = i;
        letterWidth = FONTSHEET.getWidth() / width;
    }

    public void setHeight(int i) {
        heigth = i;
        letterHeight = FONTSHEET.getHeight() / heigth;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return heigth;
    }

    private BufferedImage loadFont(String file) {
        BufferedImage font = null;
        try {
            font = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(file)));
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }

        return font;
    }

    public void loadFontArray() {
        spriteArray = new BufferedImage[letterWidth][letterHeight];

        for (int x = 0; x < letterWidth; x++) {
            for (int y = 0; y < letterHeight; y++) {
                spriteArray[x][y] = getLetter(x, y);
            }
        }
    }

    public BufferedImage getFontSheet() {
        return FONTSHEET;
    }

    public BufferedImage getLetter(int x, int y) {
        return FONTSHEET.getSubimage(x * width, y * heigth, width, heigth);
    }

    public BufferedImage getFont(char letter) {
        int value = letter;

        int x = value % letterWidth;
        int y = value / letterWidth;
        return getLetter(x, y);
    }
}

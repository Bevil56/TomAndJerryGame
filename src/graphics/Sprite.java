package graphics;

import utils.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Sprite {
    private BufferedImage spriteSheet = null;
    private BufferedImage[][] spriteArray;

    private int tileSize = 32;
    private int width;
    private int height;
    private int spriteWidth;
    private int spriteHeight;

    public static Font currentFont;


    public Sprite(String file){
        System.out.println("Loading: " + file +"...");
        spriteSheet = loadSpriteSheet(file);
        setTileSize(32);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Sprite(String file, int width, int height){
        this.width = width;
        this.height = height;

        System.out.println("Loading: " + file +"...");

        spriteSheet = loadSpriteSheet(file);

        setTileSize(32, width, height);
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
        width = tileSize;
        height = tileSize;
        spriteWidth = spriteSheet.getWidth() / width;
        spriteHeight = spriteSheet.getHeight() / height;
        loadSpriteArray();
    }
    public void setTileSize(int tileSize, int width, int height) {
        this.tileSize = tileSize;
        this.width = width;
        this.height = height;
        spriteWidth = spriteSheet.getWidth() / width;
        spriteHeight = spriteSheet.getHeight() / height;
        loadSpriteArray();
    }
    public void setSize(int width,int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setHeight(int height) {
        this.height = height;
        spriteHeight = spriteSheet.getHeight()/height;
    }

    public void setWidth(int width) {
        this.width = width;
        spriteWidth = spriteSheet.getWidth()/width;
    }

    private BufferedImage loadSpriteSheet(String file) {
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(file)));
        }catch (Exception e){
            System.out.println("ERROR: could not load file: " + file);
        }
        return sprite;
    }
    public void loadSpriteArray() {
        spriteArray = new BufferedImage[spriteHeight][spriteWidth];
            for (int y = 0 ; y < spriteHeight ; y++){
                for (int x = 0 ; x < spriteWidth ; x++){
                spriteArray[y][x] = getSprite(x,y);
            }
        }
    }
    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }
    public BufferedImage getSprite(int x,int y){
        return spriteSheet.getSubimage(x * width, y * height , width , height);
    }
    public BufferedImage getSprite(){
        return spriteSheet.getSubimage(0, 0, width , height);
    }
    public  BufferedImage[] getSpriteArray(int i){
        return spriteArray[i];
    }
    public BufferedImage[][] getSpriteArray2D(int i){
        return spriteArray;
    }
    public static void drawArray(Graphics2D g2D, String word, Vector2f pos, int size) {
        drawSprite(g2D, currentFont, word, pos, size, size, size, 0);
    }

    public static void drawArray(Graphics2D g2D, String word, Vector2f pos, int size, int xOffset) {
        drawSprite(g2D, currentFont, word, pos, size, size, xOffset, 0);
    }

    public static void drawArray(Graphics2D g2D, String word, Vector2f pos, int width, int height, int xOffset) {
        drawSprite(g2D, currentFont, word, pos, width, height, xOffset, 0);
    }

    public static void drawArray(Graphics2D g2D, Font font, String word, Vector2f pos, int size, int xOffset) {
        drawSprite(g2D, font, word, pos, size, size, xOffset, 0);
    }
    public static void drawSprite(Graphics2D g2D, ArrayList<BufferedImage>  img, Vector2f pos, int width, int height, int xOffset, int yOffset ){
        float x = pos.x;
        float y = pos.y;

        for (int i = 0; i < img.size();  i++){
            if(img.get(i) != null){
                g2D.drawImage(img.get(i), (int) x, (int) y, width, height, null );
            }
            x += xOffset;
            y += yOffset;
        }
    }
    public static void drawSprite(Graphics2D g2D, Font font, String word, Vector2f pos, int width, int height, int xOffset, int yOffset ){
        float x = pos.x;
        float y = pos.y;

        currentFont = font;

        for (int i = 0; i < word.length();  i++){
            if(word.charAt(i) != 32){
                g2D.drawImage(font.getFont(word.charAt(i)), (int) x, (int) y, width, height, null );
            }
            x += xOffset;
            y += yOffset;
        }
    }
}


package tile;

import graphics.Sprite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.pathfinder.Grid;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {
    public static ArrayList<TileMap> tm;

    private int width = 0;
    private int height = 0;

    protected static boolean[][] walkableTiles;
    protected static Grid grid;

    public TileManager() {
        tm = new ArrayList<TileMap>();
    }

    public TileManager(String path) {
        this();
        addTileMap(path, 32, 32);
    }

    public TileManager(String path, int blockWidth, int blockHeight) {
        this();
        addTileMap(path, blockWidth, blockHeight);
    }

    private void addTileMap(String path, int blockWidth, int blockHeight) {
        String imagePath;

        int tileWidth;
        int tileHeight;
        int tileColumns;
        int layers = 0;

        Sprite sprite;

        String[] data = new String[10];

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(Objects.requireNonNull(getClass().getClassLoader().getResource(path)).toURI()));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("tileset");
            Node node = list.item(0);
            Element eElement = (Element) node;

            imagePath = eElement.getAttribute("name");
            tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
            tileColumns = Integer.parseInt(eElement.getAttribute("columns"));


            sprite = new Sprite("tile/" + imagePath + ".png", tileWidth, tileHeight);

            list = doc.getElementsByTagName("layer");
            layers = list.getLength();

            for (int i = 0; i < layers; i++) {
                node = list.item(i);
                eElement = (Element) node;
                if (i <= 0) {
                    width = Integer.parseInt(eElement.getAttribute("width"));
                    height = Integer.parseInt(eElement.getAttribute("height"));
                }

                data[i] = eElement.getElementsByTagName("data").item(0).getTextContent();
//                System.out.println(imagePath + " " + width + " " + height + " " + tileCount + " " + tileColumns + " " +layers);
//                System.out.println("--------------------------------\n" + data[i]);

//                System.out.println("data["+ i + "]=" + data[i]);

                if(i >= 1) {
                    tm.add(new TileMapNorm(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns,i));}
                else {
                    tm.add(new TileMapObj(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));}
            }

        } catch (Exception e) {
            System.out.println("ERROR - TILEMANAGER: can not read tilemap:");
            e.printStackTrace();
            System.exit(0);
        }
    }
    public void render(Graphics2D g2D) {
//        System.out.println("Walkable tiles:");
//        for (int j = 0; j < getGrid().getWalkableTiles()[0].length; j++) {
//            for (int i = 0; i < getGrid().getWalkableTiles().length; i++) {
//                System.out.print(getGrid().getWalkableTiles()[i][j] ? "1 " : "0 ");
//            }
//            System.out.println();
//        }
        for (int i = 0; i < tm.size(); i++) {
            tm.get(i).render(g2D);
        }
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public static boolean[][] getWalkableTiles() {
        return TileMapNorm.getWalkableTiles();
    }

    public static Grid getGrid() {
        return TileMapNorm.getGrid();
    }
}


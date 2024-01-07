package utils.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    protected NodeTile[][] nodeTiles;

    protected static int gridWidth;
    protected static int gridHeight;

    protected static boolean[][] walkableTiles;

    public Grid(int width, int height, float[][] tile_costs) {
        gridWidth = width;
        gridHeight = height;
        nodeTiles = new NodeTile[width][height];

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                nodeTiles[x][y] = new NodeTile(x, y, tile_costs[x][y]);
    }

    public Grid(int width, int height, boolean[][] walkableTiles) {
        gridWidth = width;
        gridHeight = height;
        Grid.walkableTiles = walkableTiles;

//        System.out.println("Walkable tiles:");
//        for (int j = 0; j < getWalkableTiles()[0].length; j++) {
//            for (int i = 0; i < getWalkableTiles().length; i++) {
//                System.out.print(getWalkableTiles()[i][j] ? "1 " : "0 ");
//            }
//            System.out.println();
//        }
        nodeTiles = new NodeTile[width][height];


    }

    public List<NodeTile> get8Neighbours(NodeTile nodeTile) {
        List<NodeTile> neighbours = new ArrayList<NodeTile>();

        for (int x = -1; x <= 1; x++)
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;

                int checkX = nodeTile.x + x;
                int checkY = nodeTile.y + y;

                if (checkX >= 0 && checkX < gridWidth && checkY >= 0 && checkY < gridHeight)
                    neighbours.add(nodeTiles[checkX][checkY]);
            }

        return neighbours;
    }

    public List<NodeTile> get4Neighbours(NodeTile nodeTile) {
        List<NodeTile> neighbours = new ArrayList<NodeTile>();

        if (nodeTile.y + 1 >= 0 && nodeTile.y + 1  < gridHeight) neighbours.add(nodeTiles[nodeTile.x][nodeTile.y + 1]); // N
        if (nodeTile.y - 1 >= 0 && nodeTile.y - 1  < gridHeight) neighbours.add(nodeTiles[nodeTile.x][nodeTile.y - 1]); // S
        if (nodeTile.x + 1 >= 0 && nodeTile.x + 1  < gridHeight) neighbours.add(nodeTiles[nodeTile.x + 1][nodeTile.y]); // E
        if (nodeTile.x - 1 >= 0 && nodeTile.x - 1  < gridHeight) neighbours.add(nodeTiles[nodeTile.x - 1][nodeTile.y]); // W

        return neighbours;
    }
    public static boolean[][] getWalkableTiles() {
        return walkableTiles;
    }
    public static int getGridWidth() {
        return gridWidth;
    }

    public static int getGridHeight() {
        return gridHeight;
    }
    public NodeTile[][] getNodes() {
        return nodeTiles;
    }

    public void setNodes() {
        for (int i = 0; i < gridWidth; i++)
            for (int j = 0; j < gridHeight; j++)
                nodeTiles[i][j] = new NodeTile(i, j, getWalkableTiles()[i][j] ? 1.0f : 0.0f);;
//        for (int i = 0; i < nodeTiles.length; i++) {
//            for (int j = 0; j < nodeTiles[i].length; j++) {
//                NodeTile currentNode = nodeTiles[i][j];
//                System.out.println("NodeTile at position (" + currentNode.x + ", " + currentNode.y + ")"+": " + currentNode.price);
//            }
//        }
    }
}


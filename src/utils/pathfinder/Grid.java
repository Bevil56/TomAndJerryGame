package utils.pathfinder;

import utils.pathfinder.Node;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    protected Node[][] nodes;

    int gridWidth, gridHeight;

    protected boolean[][] walkableTiles;

    public Grid(int width, int height, float[][] tile_costs) {
        gridWidth = width;
        gridHeight = height;
        nodes = new Node[width][height];

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                nodes[x][y] = new Node(x, y, tile_costs[x][y]);
    }

    public Grid(int width, int height, boolean[][] walkableTiles) {
        gridWidth = width;
        gridHeight = height;
        this.walkableTiles = walkableTiles;

        System.out.println("Walkable tiles:");
//        for (int j = 0; j < getWalkableTiles()[0].length; j++) {
//            for (int i = 0; i < getWalkableTiles().length; i++) {
//                System.out.print(getWalkableTiles()[i][j] ? "1 " : "0 ");
//            }
//            System.out.println();
//        }
        nodes = new Node[width][height];


    }

    public List<Node> get8Neighbours(Node node) {
        List<Node> neighbours = new ArrayList<Node>();

        for (int x = -1; x <= 1; x++)
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;

                int checkX = node.x + x;
                int checkY = node.y + y;

                if (checkX >= 0 && checkX < gridWidth && checkY >= 0 && checkY < gridHeight)
                    neighbours.add(nodes[checkX][checkY]);
            }

        return neighbours;
    }

    public List<Node> get4Neighbours(Node node) {
        List<Node> neighbours = new ArrayList<Node>();

        if (node.y + 1 >= 0 && node.y + 1  < gridHeight) neighbours.add(nodes[node.x][node.y + 1]); // N
        if (node.y - 1 >= 0 && node.y - 1  < gridHeight) neighbours.add(nodes[node.x][node.y - 1]); // S
        if (node.x + 1 >= 0 && node.x + 1  < gridHeight) neighbours.add(nodes[node.x + 1][node.y]); // E
        if (node.x - 1 >= 0 && node.x - 1  < gridHeight) neighbours.add(nodes[node.x - 1][node.y]); // W

        return neighbours;
    }
    public boolean[][] getWalkableTiles() {
        return walkableTiles;
    }
    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }
    public Node[][] getNodes() {
        return nodes ;
    }

    public void setNodes() {
        for (int i = 0; i < gridWidth; i++)
            for (int j = 0; j < gridHeight; j++)
                nodes[i][j] = new Node(i, j, getWalkableTiles()[i][j] ? 1.0f : 0.0f);;
//        for (int i = 0; i < nodes.length; i++) {
//            for (int j = 0; j < nodes[i].length; j++) {
//                Node currentNode = nodes[i][j];
//                System.out.println("Node at position (" + currentNode.x + ", " + currentNode.y + ")"+": " + currentNode.price);
//            }
//        }
    }
}


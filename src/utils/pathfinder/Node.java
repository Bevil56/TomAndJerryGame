package utils.pathfinder;

public class Node {
    protected boolean walkable;
    protected int x;
    protected int y;
    protected float price;

    protected int gCost;
    protected int hCost;
    protected Node parent;

    public Node(int x, int y, float price) {
        walkable = price != 0.0f;
        this.price = price;
        this.x = x;
        this.y = y;
    }

    public int getFCost() {
        return gCost + hCost;
    }
}

package utils.pathfinder;

import entity.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class PathFinding {
    public static List<PointTile> findPath(Grid grid, Entity entity, Entity targetEntity, PointTile startPos, PointTile targetPos, boolean allowDiagonals) {
        grid.setNodes();
        startPos = entity.getPoint();
        targetPos = targetEntity.getPoint();
//        System.out.println(startPos.x+" "+startPos.y);
//        System.out.println(targetPos.x+" "+targetPos.y);
        List<Node> pathInNodes = findPathNodes(grid, startPos, targetPos, allowDiagonals);

        List<PointTile> pathInPointTiles = new ArrayList<PointTile>();

        if (pathInNodes != null)
            for (Node node : pathInNodes)
                pathInPointTiles.add(new PointTile(node.x, node.y));

        return pathInPointTiles;
    }

    private static List<Node> findPathNodes(Grid grid, PointTile startPos, PointTile targetPos, boolean allowDiagonals) {
        Node startNode = grid.getNodes()[startPos.x][startPos.y];
        Node targetNode = grid.getNodes()[targetPos.x][targetPos.y];

        List<Node> openSet = new ArrayList<Node>();
        HashSet<Node> closedSet = new HashSet<Node>();
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.get(0);

            for (int k = 1; k < openSet.size(); k++) {
                Node open = openSet.get(k);

                if (open.getFCost() < currentNode.getFCost() ||
                        open.getFCost() == currentNode.getFCost() &&
                                open.hCost < currentNode.hCost)
                    currentNode = open;
            }

            openSet.remove(currentNode);
            closedSet.add(currentNode);

            if (currentNode == targetNode)
                return retracePath(startNode, targetNode);

            List<Node> neighbours;
            if (allowDiagonals) neighbours = grid.get8Neighbours(currentNode);
            else neighbours = grid.get4Neighbours(currentNode);

            for (Node neighbour : neighbours) {
                if (!neighbour.walkable || closedSet.contains(neighbour)) continue;

                int newMovementCostToNeighbour = currentNode.gCost + getDistance(currentNode, neighbour) * (int) (10.0f * neighbour.price);
                if (newMovementCostToNeighbour < neighbour.gCost || !openSet.contains(neighbour)) {
                    neighbour.gCost = newMovementCostToNeighbour;
                    neighbour.hCost = getDistance(neighbour, targetNode);
                    neighbour.parent = currentNode;

                    if (!openSet.contains(neighbour)) openSet.add(neighbour);
                }
            }
        }

        return null;
    }

    private static List<Node> retracePath(Node startNode, Node endNode) {
        List<Node> path = new ArrayList<Node>();
        Node currentNode = endNode;

        while (currentNode != startNode) {
            path.add(currentNode);
            currentNode = currentNode.parent;
        }

        Collections.reverse(path);
        return path;
    }

    private static int getDistance(Node nodeA, Node nodeB) {
        int distanceX = Math.abs(nodeA.x - nodeB.x);
        int distanceY = Math.abs(nodeA.y - nodeB.y);

        if (distanceX > distanceY)
            return 14 * distanceY + 10 * (distanceX - distanceY);
        return 14 * distanceX + 10 * (distanceY - distanceX);
    }
}


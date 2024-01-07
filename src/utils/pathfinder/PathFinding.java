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
        List<NodeTile> pathInNodeTiles = findPathNodes(grid, startPos, targetPos, allowDiagonals);

        List<PointTile> pathInPointTiles = new ArrayList<PointTile>();

        if (pathInNodeTiles != null)
            for (NodeTile nodeTile : pathInNodeTiles)
                pathInPointTiles.add(new PointTile(nodeTile.x, nodeTile.y));

        return pathInPointTiles;
    }

    private static List<NodeTile> findPathNodes(Grid grid, PointTile startPos, PointTile targetPos, boolean allowDiagonals) {
        NodeTile startNodeTile = grid.getNodes()[startPos.x][startPos.y];
        NodeTile targetNodeTile = grid.getNodes()[targetPos.x][targetPos.y];

        List<NodeTile> openSet = new ArrayList<NodeTile>();
        HashSet<NodeTile> closedSet = new HashSet<NodeTile>();
        openSet.add(startNodeTile);

        while (!openSet.isEmpty()) {
            NodeTile currentNodeTile = openSet.get(0);

            for (int k = 1; k < openSet.size(); k++) {
                NodeTile open = openSet.get(k);

                if (open.getFCost() < currentNodeTile.getFCost() ||
                        open.getFCost() == currentNodeTile.getFCost() &&
                                open.hCost < currentNodeTile.hCost)
                    currentNodeTile = open;
            }

            openSet.remove(currentNodeTile);
            closedSet.add(currentNodeTile);

            if (currentNodeTile == targetNodeTile)
                return retracePath(startNodeTile, targetNodeTile);

            List<NodeTile> neighbours;
            if (allowDiagonals) neighbours = grid.get8Neighbours(currentNodeTile);
            else neighbours = grid.get4Neighbours(currentNodeTile);

            for (NodeTile neighbour : neighbours) {
                if (!neighbour.walkable || closedSet.contains(neighbour)) continue;

                int newMovementCostToNeighbour = currentNodeTile.gCost + getDistance(currentNodeTile, neighbour) * (int) (10.0f * neighbour.price);
                if (newMovementCostToNeighbour < neighbour.gCost || !openSet.contains(neighbour)) {
                    neighbour.gCost = newMovementCostToNeighbour;
                    neighbour.hCost = getDistance(neighbour, targetNodeTile);
                    neighbour.parent = currentNodeTile;

                    if (!openSet.contains(neighbour)) openSet.add(neighbour);
                }
            }
        }

        return null;
    }

    private static List<NodeTile> retracePath(NodeTile startNodeTile, NodeTile endNodeTile) {
        List<NodeTile> path = new ArrayList<NodeTile>();
        NodeTile currentNodeTile = endNodeTile;

        while (currentNodeTile != startNodeTile) {
            path.add(currentNodeTile);
            currentNodeTile = currentNodeTile.parent;
        }

        Collections.reverse(path);
        return path;
    }

    private static int getDistance(NodeTile nodeTileA, NodeTile nodeTileB) {
        int distanceX = Math.abs(nodeTileA.x - nodeTileB.x);
        int distanceY = Math.abs(nodeTileA.y - nodeTileB.y);

        if (distanceX > distanceY)
            return 14 * distanceY + 10 * (distanceX - distanceY);
        return 14 * distanceX + 10 * (distanceY - distanceX);
    }
}


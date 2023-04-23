package ai;

import entity.Entity;
import tile.*;

import java.util.*;

public class Pathfinder {
    
    Entity entity;
    Map<Tile, Integer> gScores = new HashMap<>();
    Map<Tile, Integer> fScores = new HashMap<>();

    public Pathfinder(Entity entity) {
        this.entity = entity;
    }

    public Tile[] reconstructPath(Map<Tile, Tile> cameFrom, Tile current) {
        ArrayList<Tile> result = new ArrayList<Tile>();
        result.add(current);
        while (cameFrom.keySet().contains(current)) {
            current = cameFrom.get(current);
            result.add(0, current);
        }
        return result.toArray(new Tile[result.size()]);
    }

    public void initializeGScores(Tile start) {
        for (int row=0; row < TileController.mapRows; row++) {
            for (int col=0; col < TileController.mapCols; col++) {
                gScores.put(TileController.map[col][row], 9999);
            }
        }
        gScores.put(start, 0);
    }

    public void initializeFScores(Tile goal) {
        for (int row=0; row < TileController.mapRows; row++) {
            for (int col=0; col < TileController.mapCols; col++) {
                fScores.put(TileController.map[col][row], TileController.getDistance(TileController.map[col][row], goal));
            }
        }
    }

    public Tile[] findPath(Entity target) {

        Tile start = entity.getTile();
        Tile goal = target.getTile();

        Map<Tile, Tile> cameFrom = new HashMap<>();

        initializeGScores(start);
        initializeFScores(goal);

        PriorityQueue<Tile> openSet = new PriorityQueue<Tile>(new OpenSetComparator());
        openSet.add(start);
        ArrayList<Tile> closedSet = new ArrayList<>();

        while (!openSet.isEmpty()) {
            //node in openset with lowest fScore value
            Tile current = openSet.poll();
            if (current == goal) {
                return reconstructPath(cameFrom, current);
            }

            closedSet.add(current);

            for (Tile neighbor : TileController.getTileNeighbors(current)) {

                if (neighbor == null || closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = gScores.get(current);

                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tentativeGScore >= gScores.get(neighbor)) {
                    continue;
                }
                
                cameFrom.put(neighbor, current);
                gScores.put(neighbor, tentativeGScore);
                fScores.put(neighbor, gScores.get(neighbor) + TileController.getDistance(neighbor, goal));

            }
        }
        return new Tile[0];
    }

    private class OpenSetComparator implements Comparator<Tile> {
        @Override
        public int compare(Tile x, Tile y) {
            // x < y means negative
            // x = y means 0
            // x > y means positive
            return fScores.get(y) - fScores.get(x);
        }
    }

}

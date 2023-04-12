package ai;

import entity.Entity;
import tile.*;

import java.util.*;

public class Pathfinder {
    
    Entity entity;

    public Pathfinder(Entity entity) {
        this.entity = entity;
    }

    public Tile[] reconstructPath(Map<Tile, Tile> cameFrom, Tile current) {
        ArrayList<Tile> result = new ArrayList<>();
        result.add(current);
        while (cameFrom.keySet().contains(current)) {
            current = cameFrom.get(current);
            result.add(0, current);
        }
        return (Tile[])result.toArray();
    }

    public Tile[] findPath(Entity target) {

        Tile start = entity.getTile();
        Tile goal = target.getTile();

        Map<Tile, Tile> cameFrom = new HashMap<>();

        Map<Tile, Integer> gScores = new HashMap<>();
        for (int row=0; row < TileController.mapRows; row++) {
            for (int col=0; col < TileController.mapCols; col++) {
                gScores.put(TileController.map[col][row], 9999);
            }
        }
        gScores.put(start, 0);

        Map<Tile, Integer> fScores = new HashMap<>();
        for (int row=0; row < TileController.mapRows; row++) {
            for (int col=0; col < TileController.mapCols; col++) {
                fScores.put(TileController.map[col][row], TileController.getDistance(TileController.map[col][row], goal));
            }
        }

        PriorityQueue<Tile> openSet = new PriorityQueue<Tile>(new OpenSetComparator(fScores));
        openSet.add(start);
        ArrayList<Tile> closedSet = new ArrayList<>();

        while (!openSet.isEmpty()) {
            //node in openset with lowest fScore value
            Tile current = openSet.poll();
            if (current == goal) {
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);
            for (Tile neighbor : TileController.getTileNeighbors(current)) {
                int tentativeGScore = gScores.get(current);
                if (tentativeGScore < gScores.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gScores.put(neighbor, tentativeGScore);
                    fScores.put(neighbor, tentativeGScore + TileController.getDistance(neighbor, goal));
                    if (openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return new Tile[0];
    }

    private class OpenSetComparator implements Comparator<Tile> {

        HashMap<Tile, Integer> fScores;

        public OpenSetComparator(Map<Tile, Integer> fScores) {
            fScores = this.fScores;
        }

        @Override
        public int compare(Tile x, Tile y) {
            // x < y means negative
            // x = y means 0
            // x > y means positive
            return fScores.get(y) - fScores.get(x);
        }
    }

}

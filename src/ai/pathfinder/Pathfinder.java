package ai.pathfinder;

import tile.Tile;
import tile.TileController;
import entity.Entity;
import entity.Zombie;

import java.util.ArrayList;
import java.lang.Math;

public class Pathfinder {
    
    Zombie zombie;
    Entity target;
    static Node[][] nodeMap;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();

    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public Pathfinder(Zombie zombie, Entity target) {
        this.zombie = zombie;
        this.target = target; // TODO - change this to look for a target on first tick after instantiation
        instantiateNodes();
    }

    public void update() {
        Tile start = zombie.getTile();
        Tile goal = target.getTile();
        if (start.equals(goal)) {
            return;
        }
        this.setNodes(start.col, start.row, goal.col, goal.row);

        if (this.search()) {
            Tile nextTile = this.pathList.get(0).toTile();
            zombie.makeNextMove(nextTile);
        }
    }

    public static void instantiateNodes() {
        nodeMap = new Node[TileController.mapCols][TileController.mapRows];
        for (int row = 0; row < TileController.mapRows; row++) {
            for (int col = 0; col < TileController.mapCols; col++) {
                nodeMap[col][row] = new Node(col, row);
            }
        }
    }

    public void resetNodes() {
        
        for (int row = 0; row < TileController.mapRows; row++) {
            for (int col = 0; col < TileController.mapCols; col++) {
                // reset open, checked, and solid state
                nodeMap[col][row].open = false;
                nodeMap[col][row].checked = false;
                nodeMap[col][row].solid = false;
            }
        }

        // reset other data structures
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;

    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();

        startNode = nodeMap[startCol][startRow];
        currentNode = startNode;
        goalNode = nodeMap[goalCol][goalRow];

        for (int row = 0; row < TileController.mapRows; row++) {
            for (int col = 0; col < TileController.mapCols; col++) {
                // set node solid property if collision is true
                nodeMap[col][row].solid = TileController.map[col][row].collision;
                // set cost
                getCost(nodeMap[col][row]);
            }
        }

    }

    public void getCost(Node node) {
        node.gCost = Math.abs(node.col - startNode.col) + Math.abs(node.row - startNode.row);
        node.hCost = Math.abs(node.col - goalNode.col) + Math.abs(node.row - goalNode.row);
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {

        while (goalReached == false && step < 500) {

            int col = currentNode.col;
            int row = currentNode.row;

            // check the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            // open the up node
            if (row - 1 >= 0) {
                openNode(nodeMap[col][row-1]);
            }
            // open the left node
            if (col - 1 >= 0) {
                openNode(nodeMap[col-1][row]);
            }
            // open the down node
            if (row + 1 < TileController.mapRows) {
                openNode(nodeMap[col][row+1]);
            }
            //open the right node
            if (col + 1 < TileController.mapCols) {
                openNode(nodeMap[col+1][row]);
            }

            // find the best node
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for (int i=0; i < openList.size(); i++) {
                // check if this node's f cost is better
                if (openList.get(i).fCost < bestNodeFCost) {
                    bestNodeIndex = i;
                    bestNodeFCost = openList.get(i).fCost;
                }
                // if f cost is equal, use g cost
                else if (openList.get(i).fCost == bestNodeFCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }

            // if there is no node in the openList, end the loop
            if (openList.size() == 0) {
                break;
            }

            // after the loop, openList[bestNodeIndex] is the next step (equals currentNode)
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
            step++;

        }

        return goalReached;

    }

    public void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackThePath() {
        Node current = goalNode;
        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }

}

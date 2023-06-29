package ai.pathfinder;

import tile.TileController;
import entity.Entity;

import java.util.ArrayList;
import java.lang.Math;

public class Pathfinder {
    
    Entity entity;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();

    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public Pathfinder(Entity entity) {
        this.entity = entity;
        instantiateNodes();
    }

    public void instantiateNodes() {
        node = new Node[TileController.mapCols][TileController.mapRows];
        for (int row = 0; row < TileController.mapRows; row++) {
            for (int col = 0; col < TileController.mapCols; col++) {
                node[col][row] = new Node(col, row);
            }
        }
    }

    public void resetNodes() {
        
        for (int row = 0; row < TileController.mapRows; row++) {
            for (int col = 0; col < TileController.mapCols; col++) {
                // reset open, checked, and solid state
                node[col][row].open = false;
                node[col][row].checked = false;
                node[col][row].solid = false;
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

        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];

        for (int row = 0; row < TileController.mapRows; row++) {
            for (int col = 0; col < TileController.mapCols; col++) {
                // set node solid property if collision is true
                node[col][row].solid = TileController.map[col][row].collision;
                // set cost
                getCost(node[col][row]);
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
                openNode(node[col][row-1]);
            }
            // open the left node
            if (col - 1 >= 0) {
                openNode(node[col-1][row]);
            }
            // open the down node
            if (row + 1 < TileController.mapRows) {
                openNode(node[col][row+1]);
            }
            //open the right node
            if (col + 1 < TileController.mapCols) {
                openNode(node[col+1][row]);
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

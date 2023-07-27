package ai.pathfinder;

import common.*;
import tile.Tile;
import entity.Entity;
import entity.creature.Creature;

import java.util.ArrayList;
import java.lang.Math;

public class Pathfinder {
    
    Entity target;
    Creature creature;
    static Node[][] nodeMap;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();

    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    Tile currentGoal;
    int pathIndex = 0;
    boolean onPath = false;

    public Pathfinder(Creature creature, Entity target) {
        this.creature = creature;
        this.target = target;
        instantiateNodes();
    }

    public void update() {
        Tile current = creature.getTile();
        Tile goal = target.getTile();
        if (current.equals(goal)) {
            return;
        }
        if (!goal.equals(currentGoal)) {
            pathIndex = 0;
            currentGoal = goal;
            this.setNodes(current.col, current.row, goal.col, goal.row);
            onPath = search();
        }
        if (onPath) {
            Tile next = this.pathList.get(pathIndex).toTile();
            if (current.equals(next)) {
                pathIndex++;
            }
            makeNextMove(next);
        }
    }

    private void makeNextMove(Tile tile) {
        Direction nextDirection = creature.getDirection(tile);
        switch (nextDirection) {
            case NORTH:
                if (creature.fitsHorizontally(tile)) {
                    creature.move(nextDirection);
                }
            break;
            case SOUTH:
                if (creature.fitsHorizontally(tile)) {
                    creature.move(nextDirection);
                }
            break;
            case EAST:
                if (creature.fitsVertically(tile)) {
                    creature.move(nextDirection);
                }
            break;
            case WEST:
                if (creature.fitsVertically(tile)) {
                    creature.move(nextDirection);
                }
            break;
        }
    }

    private static void instantiateNodes() {
        nodeMap = new Node[Tile.mapCols][Tile.mapRows];
        for (int row = 0; row < Tile.mapRows; row++) {
            for (int col = 0; col < Tile.mapCols; col++) {
                nodeMap[col][row] = new Node(col, row);
            }
        }
    }

    private void resetNodes() {
        
        for (int row = 0; row < Tile.mapRows; row++) {
            for (int col = 0; col < Tile.mapCols; col++) {
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

    private void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();

        startNode = nodeMap[startCol][startRow];
        currentNode = startNode;
        goalNode = nodeMap[goalCol][goalRow];

        for (int row = 0; row < Tile.mapRows; row++) {
            for (int col = 0; col < Tile.mapCols; col++) {
                // set node solid property if collision is true
                nodeMap[col][row].solid = Tile.map[col][row].collision;
                // set cost
                getCost(nodeMap[col][row]);
            }
        }

    }

    private void getCost(Node node) {
        node.gCost = Math.abs(node.col - startNode.col) + Math.abs(node.row - startNode.row);
        node.hCost = Math.abs(node.col - goalNode.col) + Math.abs(node.row - goalNode.row);
        node.fCost = node.gCost + node.hCost;
    }

    private boolean search() {

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
            if (row + 1 < Tile.mapRows) {
                openNode(nodeMap[col][row+1]);
            }
            //open the right node
            if (col + 1 < Tile.mapCols) {
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

    private void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    private void trackThePath() {
        Node current = goalNode;
        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }

}

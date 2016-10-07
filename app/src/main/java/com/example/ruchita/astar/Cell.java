package com.example.ruchita.astar;

/**
 * Created by Kelay on 1/10/16.
 */
public class Cell {
    int heuristicCost = 0; //Heuristic cost
    int g;
    int finalCost = 0; //G+H
    int i, j;
    Cell parent;

    Cell(int i, int j){
        this.i = i;
        this.j = j;
    }
}

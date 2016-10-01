package com.example.ruchita.astar;

/**
 * Created by ruchita on 30/9/16.
 */
public class Cell {
    int heuristicCost = 0; //Heuristic cost
    int finalCost = 0; //G+H
    int i, j;
    Cell parent;

    Cell(int i, int j){
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString(){
        return "["+this.i+", "+this.j+"]";
    }
}

package com.example.ruchita.astar;

import android.util.Log;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by Kelay on 1/10/16.
 */
public class Astar {
    //public static final int DIAGONAL_COST = 1;
    //public static final int V_H_COST = 1;

    //Blocked cells are just null Cell values in grid
    static Cell [][] grid = new Cell[8][8];

    static PriorityQueue<Cell> open;

    static boolean closed[][];
    static int startI, startJ;
    static int endI, endJ;

    private static void setStartCell(int i, int j){
        startI = i;
        startJ = j;
    }

    private static void setEndCell(int i, int j){
        endI = i;
        endJ = j;
    }

    private static void checkAndUpdateCost(Cell current, Cell t, int cost){
        if(t == null || closed[t.i][t.j])return;
        int t_final_cost = t.heuristicCost+cost;

        boolean inOpen = open.contains(t);
        if(!inOpen || t_final_cost<t.finalCost){
            t.finalCost = t_final_cost;
            t.parent = current;
            if(!inOpen)open.add(t);
        }
    }

    private static void AStarSearch(){

        //add the start location to open list.
        open.add(grid[startI][startJ]);

        Cell current;

        while(true){
            current = open.poll();
            if(current==null){
                //Log.d("hhajj","current went null...........................................................");
                break;
            }
            closed[current.i][current.j]=true;

            if(current.equals(grid[endI][endJ])){
                //Log.d("hha","reached dest...........................................................");
                return;
            }

            Cell t;
            if(current.i-1>=0){
                t = grid[current.i-1][current.j];
                checkAndUpdateCost(current, t, current.g+1);

                if(current.j-1>=0){
                    t = grid[current.i-1][current.j-1];
                    checkAndUpdateCost(current, t, current.g+1);
                }

                if(current.j+1<grid[0].length){
                    t = grid[current.i-1][current.j+1];
                    checkAndUpdateCost(current, t, current.g+1);
                }
            }

            if(current.j-1>=0){
                t = grid[current.i][current.j-1];
                checkAndUpdateCost(current, t, current.g+1);
            }

            if(current.j+1<grid[0].length){
                t = grid[current.i][current.j+1];
                checkAndUpdateCost(current, t, current.g+1);
            }

            if(current.i+1<grid.length){
                t = grid[current.i+1][current.j];
                checkAndUpdateCost(current, t, current.g+1);

                if(current.j-1>=0){
                    t = grid[current.i+1][current.j-1];
                    checkAndUpdateCost(current, t, current.g+1);
                }

                if(current.j+1<grid[0].length){
                    t = grid[current.i+1][current.j+1];
                    checkAndUpdateCost(current, t, current.g+1);
                }
            }
        }
    }

    /*
    Params :
    tCase = test case No.
    x, y = Board's dimensions
    si, sj = start location's x and y coordinates
    ei, ej = end location's x and y coordinates
    int[][] blocked = array containing inaccessible cell coordinates
    */
    public static Stack<Pair> getShortestPath(int si, int sj, int ei, int ej, int[][] blocked){
        /*for(int i=0;i<8;++i){
            for(int j=0;j<8;++j) {
                Log.d("hy","f "+blocked[i][j]);
            }
        }*/

        grid = new Cell[8][8];
        closed = new boolean[8][8];
        open = new PriorityQueue<Cell>(64,new Comparator<Cell>(){
            @Override
            public int compare(Cell c1, Cell c2) {
                return c1.finalCost < c2.finalCost ? -1 :
                        c1.finalCost > c2.finalCost ? 1 : 00;
            }
        });

        if(si<0 || si>7 || sj<0 | sj>7 || ei<0 || ei>7 || ej<0 | ej>7){
            return null;
        }

        //Set start position
        setStartCell(si, sj);

        //Set End Location
        setEndCell(ei, ej);

        for(int i=0;i<8;++i){
            for(int j=0;j<8;++j){
                closed[i][j]=false;
                if(blocked[i][j]==0){
                    grid[i][j] = null;
                }
                else{
                    grid[i][j] = new Cell(i, j);
                    //grid[i][j].heuristicCost =(int)Math.sqrt((Math.abs(i-endI)*Math.abs(i-endI))+(Math.abs(j-endJ)*Math.abs(j-endJ)));
                    grid[i][j].heuristicCost = Math.max((Math.abs(i-endI)),(Math.abs(j-endJ)));
                }
            }
        }

        grid[si][sj].g=0;
        grid[si][sj].finalCost = 0;

        AStarSearch();

        if(closed[endI][endJ]){
           // Log.d("hhdhhgd","Hello");
            Stack<Pair> path = new Stack<>();
            Cell current = grid[endI][endJ];
            while(current!=null){
                path.add(new Pair(current.i,current.j));
                //Log.d("hhdhhgdg","Hello1");
                current = current.parent;
            }
            return path;
        }
        else
            return null;
    }
}


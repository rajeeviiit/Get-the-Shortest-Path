package com.example.ruchita.astar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;
import java.util.logging.LogRecord;

public class GameActivity extends AppCompatActivity {

    TextView tv[][]=new TextView[8][8];

    int textViews[][] = {{R.id.tv00,R.id.tv01,R.id.tv02,R.id.tv03,R.id.tv04,R.id.tv05,R.id.tv06,R.id.tv07},
            {R.id.tv10,R.id.tv11,R.id.tv12,R.id.tv13,R.id.tv14,R.id.tv15,R.id.tv16,R.id.tv17},
            {R.id.tv20,R.id.tv21,R.id.tv22,R.id.tv23,R.id.tv24,R.id.tv25,R.id.tv26,R.id.tv27},
            {R.id.tv30,R.id.tv31,R.id.tv32,R.id.tv33,R.id.tv34,R.id.tv35,R.id.tv36,R.id.tv37},
            {R.id.tv40,R.id.tv41,R.id.tv42,R.id.tv43,R.id.tv44,R.id.tv45,R.id.tv46,R.id.tv47},
            {R.id.tv50,R.id.tv51,R.id.tv52,R.id.tv53,R.id.tv54,R.id.tv55,R.id.tv56,R.id.tv57},
            {R.id.tv60,R.id.tv61,R.id.tv62,R.id.tv63,R.id.tv64,R.id.tv65,R.id.tv66,R.id.tv67},
            {R.id.tv70,R.id.tv71,R.id.tv72,R.id.tv73,R.id.tv74,R.id.tv75,R.id.tv76,R.id.tv77}};

    int state;
    boolean isStartSet,isEndSet;

    LinearLayout start,end,unblock,block;
    Button resetButton,playButton;

    int[][] grid=new int[8][8];

    Astar game = new Astar();
    Stack<Pair> shortestPath;

    int sx,sy,ex,ey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initiateVariables();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<8;i++){
                    for(int j=0;j<8;j++){
                        tv[i][j].setBackgroundResource(R.drawable.whitebox);
                    }
                }

                isStartSet=false;
                isEndSet=false;

                for(int i=0;i<8;i++){
                    for(int j=0;j<8 ;j++ ){
                        grid[i][j] = 1;
                    }
                }

                state=2;
                sx=sy=ex=ey=-1;
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state=4;
                shortestPath = game.getShortestPath(sx,sy,ex,ey,grid);
                if(shortestPath==null){
                    Toast.makeText(GameActivity.this,"No possible path!:( Please Try Again!",Toast.LENGTH_SHORT).show();
                }
                else {

                    int l = shortestPath.size();
                    shortestPath.pop();
                    for (int i = 0; i < l-2; i++) {
                        Pair k = shortestPath.pop();
                        //Log.d("fs","final stack "+i+" "+k.x+ " "+k.y);
                        tv[k.x][k.y].setBackgroundColor(getResources().getColor(R.color.yellow));

                    }
                }
            }
        });
    }

    public void setBox(View view){

        int viewId=view.getId();
        int value=(viewId%100) - 17;
        int i=value/8;
        int j=value%8;

        if((state==2)){

            //Log.d("In state","Unblocking but blocking!"+state);

            if(grid[i][j]==-1){
                isStartSet=false;
                sx=sy=-1;
            }else if(grid[i][j]==-2){
                isEndSet=false;
                ex=ey=-1;
            }

            grid[i][j]=1;
            tv[i][j].setBackgroundResource(R.drawable.whitebox);

        }
        else if(state==0){

//            Log.d("In state","Start");

            if(isStartSet){
                Toast.makeText(this,"You can not set start position more than once!!",Toast.LENGTH_SHORT).show();
                return;
            }else{
                isStartSet=true;
                sx=i;
                sy=j;
                grid[i][j]=-1;
                tv[i][j].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        }
        else if(state == 1){

//            Log.d("In state","End");

            if(isEndSet){
                Toast.makeText(this,"You can not set end position more than once!!",Toast.LENGTH_SHORT).show();
                return;
            }else{
                isEndSet=true;
                ex=i;
                ey=j;
                grid[i][j] =-2;
                tv[i][j].setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        }
        else if(state == 3){

//            Log.d("In state","Block");

            if(grid[i][j]==-1){
                isStartSet=false;
                sx=sy=-1;
            }else if(grid[i][j]==-2){
                isEndSet=false;
                ex=ey-1;
            }
            grid[i][j]=0;
            tv[i][j].setBackgroundColor(getResources().getColor(R.color.black));
        }


    }

    public void changeBlockColor(View view){

        if(view.equals(start)){
            state=0;
        }else if(view.equals(end)){
            state=1;
        }else if(view.equals(unblock)){
            state=2;
        }else if(view.equals(block)){
            state=3;
        }
    }

    private void initiateVariables(){
        resetButton = (Button)findViewById(R.id.resetbtn);
        playButton = (Button)findViewById(R.id.playbtn);

        start = (LinearLayout)findViewById(R.id.startll);
        end = (LinearLayout)findViewById(R.id.endll);
        unblock = (LinearLayout)findViewById(R.id.unblockedll);
        block = (LinearLayout)findViewById(R.id.blockll);

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                tv[i][j]=(TextView)findViewById(textViews[i][j]);
            }
        }

        for(int i=0;i<8;i++){
            for(int j=0;j<8 ;j++ ){
                grid[i][j] = 1;
            }
        }

        sx=sy=ex=ey=-1;
        state=2;
        isStartSet=false;
        isEndSet=false;
    }
}

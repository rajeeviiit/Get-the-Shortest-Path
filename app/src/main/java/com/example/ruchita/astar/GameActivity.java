package com.example.ruchita.astar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

//    TextView tv00,tv01,tv02,tv03,tv04,tv05,tv06,tv07,tv10,tv11,tv12,tv13,tv14,tv15,tv16,tv17,tv20,tv21,tv22,tv23,tv24,tv25,tv26,tv27;
//    TextView tv30,tv31,tv32,tv33,tv34,tv35,tv36,tv37,tv40,tv50,tv51,tv52,tv53,tv54,tv55,tv56,tv57,tv60,tv61,tv62,tv63,tv64;
//    TextView tv65,tv66,tv67,tv41,tv42,tv43,tv44,tv45,tv46,tv47,tv70,tv71,tv72,tv73,tv74,tv75,tv76,tv77;

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

    int[][] shortestpath={{1,2},{2,3}};

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

                //=test(grid,sx,sy,ex,ey);



                for(int i=0;i<shortestpath[0].length;i++){

                    tv[shortestpath[i][0]][shortestpath[i][1]].setBackgroundColor(getResources().getColor(R.color.yellow));

                }
            }
        });
    }

    public void setBox(View view){

        int viewId=view.getId();
        int value=(viewId%100) - 17;
        int i=value/8;
        int j=value%8;

        if((state==2)){//||((grid[i][j]!=1)&&(state!=3))||((grid[i][j]==0)&&(state==3))){

            //Log.d("In state","Unblocking but blocking!"+state);

            if(grid[i][j]==-1){
                isStartSet=false;
            }else if(grid[i][j]==-2){
                isEndSet=false;
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
            }else if(grid[i][j]==-2){
                isEndSet=false;
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

//        tv00 = (TextView)findViewById(R.id.tv00);
//        tv01 = (TextView)findViewById(R.id.tv01);
//        tv02 = (TextView)findViewById(R.id.tv02);
//        tv03 = (TextView)findViewById(R.id.tv03);
//        tv04 = (TextView)findViewById(R.id.tv04);
//        tv05 = (TextView)findViewById(R.id.tv05);
//        tv06 = (TextView)findViewById(R.id.tv06);
//        tv07 = (TextView)findViewById(R.id.tv07);
//        tv10 = (TextView)findViewById(R.id.tv10);
//        tv11 = (TextView)findViewById(R.id.tv11);
//        tv12 = (TextView)findViewById(R.id.tv12);
//        tv13 = (TextView)findViewById(R.id.tv13);
//        tv14 = (TextView)findViewById(R.id.tv14);
//        tv15 = (TextView)findViewById(R.id.tv15);
//        tv16 = (TextView)findViewById(R.id.tv16);
//        tv17 = (TextView)findViewById(R.id.tv17);
//        tv20 = (TextView)findViewById(R.id.tv20);
//        tv21 = (TextView)findViewById(R.id.tv21);
//        tv22 = (TextView)findViewById(R.id.tv22);
//        tv23 = (TextView)findViewById(R.id.tv23);
//        tv24 = (TextView)findViewById(R.id.tv24);
//        tv25 = (TextView)findViewById(R.id.tv25);
//        tv26 = (TextView)findViewById(R.id.tv26);
//        tv27 = (TextView)findViewById(R.id.tv27);
//        tv30 = (TextView)findViewById(R.id.tv30);
//        tv31 = (TextView)findViewById(R.id.tv31);
//        tv32 = (TextView)findViewById(R.id.tv32);
//        tv33 = (TextView)findViewById(R.id.tv33);
//        tv34 = (TextView)findViewById(R.id.tv34);
//        tv35 = (TextView)findViewById(R.id.tv35);
//        tv36 = (TextView)findViewById(R.id.tv36);
//        tv37 = (TextView)findViewById(R.id.tv37);
//        tv40 = (TextView)findViewById(R.id.tv40);
//        tv41 = (TextView)findViewById(R.id.tv41);
//        tv42 = (TextView)findViewById(R.id.tv42);
//        tv43 = (TextView)findViewById(R.id.tv43);
//        tv44 = (TextView)findViewById(R.id.tv44);
//        tv45 = (TextView)findViewById(R.id.tv45);
//        tv46 = (TextView)findViewById(R.id.tv46);
//        tv47 = (TextView)findViewById(R.id.tv47);
//        tv50 = (TextView)findViewById(R.id.tv50);
//        tv51 = (TextView)findViewById(R.id.tv51);
//        tv52 = (TextView)findViewById(R.id.tv52);
//        tv53 = (TextView)findViewById(R.id.tv53);
//        tv54 = (TextView)findViewById(R.id.tv54);
//        tv55 = (TextView)findViewById(R.id.tv55);
//        tv56 = (TextView)findViewById(R.id.tv56);
//        tv57 = (TextView)findViewById(R.id.tv57);
//        tv60 = (TextView)findViewById(R.id.tv60);
//        tv61 = (TextView)findViewById(R.id.tv61);
//        tv62 = (TextView)findViewById(R.id.tv62);
//        tv63 = (TextView)findViewById(R.id.tv63);
//        tv64 = (TextView)findViewById(R.id.tv64);
//        tv65 = (TextView)findViewById(R.id.tv65);
//        tv66 = (TextView)findViewById(R.id.tv66);
//        tv67 = (TextView)findViewById(R.id.tv67);
//        tv70 = (TextView)findViewById(R.id.tv70);
//        tv71 = (TextView)findViewById(R.id.tv71);
//        tv72 = (TextView)findViewById(R.id.tv72);
//        tv73 = (TextView)findViewById(R.id.tv73);
//        tv74 = (TextView)findViewById(R.id.tv74);
//        tv75 = (TextView)findViewById(R.id.tv75);
//        tv76 = (TextView)findViewById(R.id.tv76);
//        tv77 = (TextView)findViewById(R.id.tv77);

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

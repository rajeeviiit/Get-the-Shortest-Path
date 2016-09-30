package com.example.ruchita.astar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView tv00,tv01,tv02,tv03,tv04,tv05,tv06,tv07,tv10,tv11,tv12,tv13,tv14,tv15,tv16,tv17,tv20,tv21,tv22,tv23,tv24,tv25,tv26,tv27;
    TextView tv30,tv31,tv32,tv33,tv34,tv35,tv36,tv37,tv40,tv50,tv51,tv52,tv53,tv54,tv55,tv56,tv57,tv60,tv61,tv62,tv63,tv64;
    TextView tv65,tv66,tv67,tv41,tv42,tv43,tv44,tv45,tv46,tv47,tv70,tv71,tv72,tv73,tv74,tv75,tv76,tv77;
    LinearLayout start,end,unblock,block;
    Button resetButton,playButton;

    int[][] grid=new int[8][8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initiatevariables();
    }

    public void setBox(View view){

    }

    public void changeBlockColor(View view){
        
    }

    private void initiatevariables(){
        resetButton = (Button)findViewById(R.id.resetbtn);
        playButton = (Button)findViewById(R.id.playbtn);

        start = (LinearLayout)findViewById(R.id.startll);
        end = (LinearLayout)findViewById(R.id.endll);
        unblock = (LinearLayout)findViewById(R.id.unblockedll);
        block = (LinearLayout)findViewById(R.id.blockll);

        tv00 = (TextView)findViewById(R.id.tv00);
    }
}

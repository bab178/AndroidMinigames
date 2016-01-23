package com.example.blake.androidminigames;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class Minesweeper extends Activity implements View.OnClickListener{

    private Button flags;
    private boolean flag_state = false;
    private GridView gridview;
    private Integer[] mineField;
    private int gridSize;
    private int num_mines = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minesweeper);

        flags = (Button) findViewById(R.id.ms_flag_btn);

        gridview = (GridView) findViewById(R.id.grid);
        gridview.setAdapter(new ImageAdapter(this));

        newGame();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(Minesweeper.this, "" + position, Toast.LENGTH_SHORT).show();
                if(flag_state) {
                   //Place flags
                }
                else {
                    if(bombSquad(position) == 0) {
                        //Clear map recursively
                    }
                    else {
                        //Hit a bomb, game over
                    }
                }
            }
        });

        flags.setOnClickListener(this);
    }

    private void newGame() {
        gridSize = gridview.getAdapter().getCount();
        mineField = new Integer[gridSize];

        for(int i = 0 ; i < gridSize; i++) {
            boolean r = Math.random() < 0.16;
            mineField[i] = r ? 1 : 0;
            if(r) num_mines++;
        }
    }

    private Integer bombSquad(int position) {
        return mineField[position];
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ms_flag_btn:
                if(!flag_state) {
                    flags.setText(R.string.flags_on);
                    flag_state = true;
                }
                else {
                    flags.setText(R.string.flags_off);
                    flag_state = false;
                }
                break;
        }
    }
}

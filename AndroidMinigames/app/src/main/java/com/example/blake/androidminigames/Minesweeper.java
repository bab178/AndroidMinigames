package com.example.blake.androidminigames;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

public class Minesweeper extends Activity implements View.OnClickListener{

    private Button flags;
    private boolean flag_state = false;
    private GridView gridview;
    private Integer[] mineField;
    private int gridSize;
    private int num_mines = 0, flags_used = 0, num_mines_flagged = 0;
    private ImageAdapter imageAdapter;
    private boolean gameOver = false;

    private Integer MS_MINE = R.drawable.ms_mine, MS_FLAG = R.drawable.ms_flag,
            MS_X = R.drawable.ms_x, MS_BLANK = R.drawable.ms_blank, MS_EMPTY = R.drawable.ms_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minesweeper);

        flags = (Button) findViewById(R.id.ms_flag_btn);
        gridview = (GridView) findViewById(R.id.grid);

        newGame();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if(flag_state && num_mines - flags_used > 0) {
                   //Place flags

                    //Undo place flag
                    if(imageAdapter.getItem(position) == MS_FLAG) {
                        imageAdapter.setTile(position, MS_EMPTY, v, parent);
                        flags_used--;
                    }
                    else { // Place new flag
                        imageAdapter.setTile(position, MS_FLAG, v, parent);
                        flags_used++;
                        if(mineField[position] == 1)
                            num_mines_flagged++;
                    }

                }
                else {
                    if(bombSquad(position) == 0) {
                        //Clear map recursively
                        imageAdapter.setTile(position, MS_BLANK, v, parent);

                        if(num_mines_flagged - flags_used == 0 && !gameOver) {
                            //VICTORY
                            gameOver = true;
                            flags.setText(R.string.victory);

                            for(int i = 0; i < gridSize; i++) {
                                if(imageAdapter.getItem(position) == MS_FLAG) {
                                    imageAdapter.setTile(position, MS_MINE, v, parent);
                                }
                            }

                        }
                    }
                    else {
                        //Hit a bomb, game over
                        imageAdapter.setTile(position, MS_X, v, parent);
                        gameOver = true;
                        flags.setText(R.string.reset);
                    }
                }
            }
        });

        flags.setOnClickListener(this);
    }

    private Integer bombSquad(int position) {
        return mineField[position];
    }

    private void newGame() {
        Log.d("Number of mines: "+num_mines, "Flags used: "+flags_used);
        gameOver = false;
        imageAdapter = new ImageAdapter(this);
        gridview.setAdapter(imageAdapter);

        num_mines = 0;
        flags_used = 0;
        num_mines_flagged = 0;

        gridSize = imageAdapter.getCount();
        mineField = new Integer[gridSize];

        for(int i = 0 ; i < gridSize; i++) {
            //Chance to place mine
            boolean r = Math.random() < 0.16;

            //Boolean array of mines
            //Mine: 1, Clear: 0
            mineField[i] = r ? 1 : 0;

            //Count mines
            if(r) {num_mines++;}
        }
    }

    @Override
    public void onClick(View v) {
        String flags_str = " (" + (num_mines - flags_used) + " LEFT)";
        switch(v.getId()) {
            case R.id.ms_flag_btn:
                if(!flag_state) {
                    flags.setText(getString(R.string.flags_on) + flags_str);
                    flag_state = true;
                }
                else {
                    flags.setText(getString(R.string.flags_off) + flags_str);
                    flag_state = false;
                }
                if(gameOver) {
                    newGame();
                }
                break;
        }
    }
}

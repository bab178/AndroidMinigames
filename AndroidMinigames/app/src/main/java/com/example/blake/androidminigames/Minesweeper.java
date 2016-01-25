package com.example.blake.androidminigames;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

public class Minesweeper extends Activity implements View.OnClickListener{

    private ImageButton flags;
    private Button reset;
    private boolean flag_state = false;
    private GridView gridview;
    private boolean[][] mineField;
    private int num_mines = 0, flags_used = 0, num_mines_flagged = 0;
    private ImageAdapter imageAdapter;
    private boolean gameOver = false;
    private int num_cols = 6;
    private int num_rows = 9;

    private Integer MS_MINE = R.drawable.ms_mine, MS_FLAG = R.drawable.ms_flag,
            MS_X = R.drawable.ms_x, MS_BLANK = R.drawable.ms_blank, MS_EMPTY = R.drawable.ms_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minesweeper);

        flags = (ImageButton) findViewById(R.id.ms_flag_btn);
        reset = (Button) findViewById(R.id.ms_reset_btn);
        flags.setOnClickListener(this);
        reset.setOnClickListener(this);

        gridview = (GridView) findViewById(R.id.grid);

        newGame();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                int xpos = position%num_cols;
                int ypos = position/num_cols;
                Log.d("x"+xpos, "y"+ypos);
                if(flag_state && num_mines - flags_used > 0) { //Place flags
                    if(imageAdapter.getItem(xpos, ypos) == MS_FLAG) { //Undo place flag
                        imageAdapter.setTile(xpos, ypos, MS_EMPTY, v, parent);
                        flags_used--;
                    }
                    else { // Place new flag
                        imageAdapter.setTile(xpos, ypos, MS_FLAG, v, parent);
                        flags_used++;
                        if(mineField[xpos][ypos])
                            num_mines_flagged++;
                    }
                }
                else {
                    if(!bombSquad(xpos,ypos)) { //Hit empty space
                        //TODO: Clear map recursively

                        //Show surrounding mines
                        switch (countMines(xpos,ypos)) {
                            case 0: imageAdapter.setTile(xpos, ypos, MS_BLANK, v, parent); break;
                            case 1: imageAdapter.setTile(xpos, ypos, R.drawable.ms_1, v, parent); break;
                            case 2: imageAdapter.setTile(xpos, ypos, R.drawable.ms_2, v, parent); break;
                            case 3: imageAdapter.setTile(xpos, ypos, R.drawable.ms_3, v, parent); break;
                            case 4: imageAdapter.setTile(xpos, ypos, R.drawable.ms_4, v, parent); break;
                            case 5: imageAdapter.setTile(xpos, ypos, R.drawable.ms_5, v, parent); break;
                            case 6: imageAdapter.setTile(xpos, ypos, R.drawable.ms_6, v, parent); break;
                            case 7: imageAdapter.setTile(xpos, ypos, R.drawable.ms_7, v, parent); break;
                            case 8: imageAdapter.setTile(xpos, ypos, R.drawable.ms_8, v, parent); break;
                        }


                        if(num_mines_flagged - flags_used == 0 && !gameOver) {
                            //VICTORY
                            gameOver = true;


                            //Reveal mines
                            for(int i = 0; i < num_cols; i++)
                                for (int j = 0; j < num_rows; j++)
                                    if(imageAdapter.getItem(i, j) == MS_FLAG)
                                        imageAdapter.setTile(i, j, MS_MINE, v, parent);
                        }
                    }
                    else { //Hit a bomb, game over
                        imageAdapter.setTile(xpos, ypos, MS_X, v, parent);
                        gameOver = true;
                    }
                }
            }
        });
    }

    private boolean bombSquad(int x, int y) {
        return mineField[x][y];
    }

    private void newGame() {
        Log.d("Number of mines "+num_mines, "Flags used "+flags_used);
        gameOver = false;
        imageAdapter = new ImageAdapter(getApplicationContext(), new int[num_cols][num_rows]);
        gridview.setAdapter(imageAdapter);

        flags.setImageDrawable(getResources().getDrawable(R.drawable.ms_blank));
        flag_state = false;

        num_mines = 0;
        flags_used = 0;
        num_mines_flagged = 0;

        mineField = new boolean[num_cols][num_rows];

        for(int i = 0; i < num_cols; i++)
            for (int j = 0; j < num_rows; j++) {
            //Chance to place mine in boolean array of mines
            mineField[i][j] = Math.random() < 0.2;

            //Count mines
            if(mineField[i][j]) {num_mines++;}
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ms_flag_btn:
                if(!flag_state) {
                    flags.setImageDrawable(getResources().getDrawable(R.drawable.ms_flag));
                    flag_state = true;
                }
                else {
                    flags.setImageDrawable(getResources().getDrawable(R.drawable.ms_blank));
                    flag_state = false;
                }
                break;
            case R.id.ms_reset_btn: newGame(); break;
        }
    }

    private int countMines(int i, int j) {
        int count = 0;
        if(i == 0 && j == 0) {
            if(mineField[i + 1][j + 1]) count++;
            if(mineField[i + 1][j]) count++;
            if(mineField[i][j + 1]) count++;
        }
        //bottom left corner
        else if(i == num_rows-1  && j == 0) {
            if(mineField[i - 1][j + 1]) count++;
            if(mineField[i - 1][j]) count++;
            if(mineField[i][j + 1]) count++;
        }
        //top right corner
        else if(i == 0 && j == num_cols-1) {
            if(mineField[i + 1][j - 1]) count++;
            if(mineField[i + 1][j]) count++;
            if(mineField[i][j - 1]) count++;
        }
        //Bottom right corner
        else if(i == num_rows-1 && j == num_cols-1) {
            if(mineField[i - 1][j - 1]) count++;
            if(mineField[i - 1][j]) count++;
            if(mineField[i][j - 1]) count++;
        }
        //Top Row Checks
        else if(i == 0 && (j != 0 && j != num_cols)) {
            if(mineField[i][j + 1]) count++;
            if(mineField[i][j - 1]) count++;
            if(mineField[i + 1][j + 1]) count++;
            if(mineField[i + 1][j - 1]) count++;
            if(mineField[i + 1][j]) count++;
        }
        //Bottom Row checks
        else if(i == num_rows-1 && (j != 0 && j != num_cols)) {
            if(mineField[i][j + 1]) count++;
            if(mineField[i][j - 1]) count++;
            if(mineField[i - 1][j - 1]) count++;
            if(mineField[i - 1][j + 1]) count++;
            if(mineField[i - 1][j]) count++;
        }
        //Left Side Checks
        else if(j == 0 && (i != 0 && i != num_rows)) {
            if(mineField[i][j + 1]) count++;
            if(mineField[i + 1][j]) count++;
            if(mineField[i - 1][j]) count++;
            if(mineField[i - 1][j + 1]) count++;
            if(mineField[i + 1][j + 1]) count++;
        }
        //Right Side Checks
        else if(j == num_cols-1 && (i != 0 && i != num_rows)) {
            if(mineField[i][j - 1]) count++;
            if(mineField[i + 1][j]) count++;
            if(mineField[i - 1][j]) count++;
            if(mineField[i - 1][j - 1]) count++;
            if(mineField[i + 1][j - 1]) count++;
        }
        //Middle checks
        else {
            if(mineField[i - 1][j + 1]) count++;
            if(mineField[i][j + 1]) count++;
            if(mineField[i][j - 1]) count++;
            if(mineField[i + 1][j]) count++;
            if(mineField[i - 1][j]) count++;
            if(mineField[i + 1][j + 1]) count++;
            if(mineField[i - 1][j - 1]) count++;
            if(mineField[i + 1][j - 1]) count++;
        }
        Log.d("Mine Count", ""+count);
        return count;
    }
}

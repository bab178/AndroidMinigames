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
    private boolean flag_state = false;
    private GridView gridview;
    private boolean[][] mineField;
    private int num_mines = 0, flags_used = 0, num_mines_flagged = 0;
    private ImageAdapter imageAdapter;
    private boolean gameOver = false;
    private int num_cols = 6;
    private int num_rows = 6;

    private Integer MS_MINE = R.drawable.ms_mine, MS_FLAG = R.drawable.ms_flag,
            MS_X = R.drawable.ms_x, MS_BLANK = R.drawable.ms_blank, MS_EMPTY = R.drawable.ms_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minesweeper);

        flags = (ImageButton) findViewById(R.id.ms_flag_btn);
        Button reset = (Button) findViewById(R.id.ms_reset_btn);
        flags.setOnClickListener(this);
        reset.setOnClickListener(this);

        gridview = (GridView) findViewById(R.id.grid);

        newGame();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                int xpos = position/num_rows;
                int ypos = position%num_rows;
                Log.d(" ROW"+xpos, "COL"+ypos);
                if(flag_state && num_mines - flags_used > 0) { //Place flags
                    if(imageAdapter.getItem(xpos, ypos) == MS_FLAG) { //Undo place flag
                        imageAdapter.setTile(xpos, ypos, position, MS_EMPTY, v, parent);
                        flags_used--;
                    }
                    else { // Place new flag
                        imageAdapter.setTile(xpos, ypos, position, MS_FLAG, v, parent);
                        flags_used++;
                        if(mineField[xpos][ypos])
                            num_mines_flagged--;
                    }
                }
                else if(!gameOver){
                    if(!mineField[xpos][ypos]) { //Hit empty space
                        //TODO: Clear map recursively

                        switch (countMines(xpos,ypos)) { //Count surrounding mines
                            case 0: imageAdapter.setTile(xpos, ypos, position, MS_BLANK, v, parent); break;
                            case 1: imageAdapter.setTile(xpos, ypos, position, R.drawable.ms_1, v, parent); break;
                            case 2: imageAdapter.setTile(xpos, ypos, position, R.drawable.ms_2, v, parent); break;
                            case 3: imageAdapter.setTile(xpos, ypos, position, R.drawable.ms_3, v, parent); break;
                            case 4: imageAdapter.setTile(xpos, ypos, position, R.drawable.ms_4, v, parent); break;
                            case 5: imageAdapter.setTile(xpos, ypos, position, R.drawable.ms_5, v, parent); break;
                            case 6: imageAdapter.setTile(xpos, ypos, position, R.drawable.ms_6, v, parent); break;
                            case 7: imageAdapter.setTile(xpos, ypos, position, R.drawable.ms_7, v, parent); break;
                            case 8: imageAdapter.setTile(xpos, ypos, position, R.drawable.ms_8, v, parent); break;
                            default: imageAdapter.setTile(xpos, ypos, position, MS_BLANK, v, parent); break;
                        }

                        if(num_mines_flagged + flags_used == 0 && !gameOver) { //VICTORY
                            gameOver = true;

                            for(int i = 0; i < num_rows; i++)  //Reveal mines
                                for (int j = 0; j < num_cols; j++)
                                    if(mineField[i][j])
                                        imageAdapter.setTile(i, j, position, MS_MINE, v, parent);
                        }
                    }
                    else{ //Hit a bomb, game over
                        Log.d("HIT A MINE","GAME OVER");
                        imageAdapter.setTile(xpos, ypos, position, MS_X, v, parent);
                        gameOver = true;
                        for(int i = 0; i < num_rows; i++)  //Reveal mines
                            for (int j = 0; j < num_cols; j++)
                                if(mineField[i][j])
                                    imageAdapter.setTile(i, j, position, MS_MINE, v, parent);
                    }
                }
                imageAdapter.notifyDataSetChanged(); // Update grid
            }
        });
    }

    private void newGame() {
        Log.d("NEW GAME", "Number of mines("+num_mines+")");
        gameOver = false;
        imageAdapter = new ImageAdapter(getApplicationContext(), new int[num_rows][num_cols]);
        gridview.setAdapter(imageAdapter);

        flags.setImageDrawable(getResources().getDrawable(R.drawable.ms_blank));
        flag_state = false;

        num_mines = 0;
        flags_used = 0;


        mineField = new boolean[num_rows][num_cols];

        for(int i = 0; i < num_rows; i++)
            for (int j = 0; j < num_cols; j++) {
            //Chance to place mine in boolean array of mines
            mineField[i][j] = Math.random() < 0.2;

            //Count mines
            if(mineField[i][j]) {num_mines++;}
        }
        num_mines_flagged = num_mines;
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

        for(int tempRow = i-1; tempRow <= i+1; tempRow++)  //go around current position
            for(int tempCol = j-1; tempCol <= j+1; tempCol++)
                if(tempRow >= 0 && tempRow < num_rows && tempCol >= 0 &&
                   tempCol < num_cols && tempRow != i && tempCol != j &&
                   mineField[tempRow][tempCol])
                    count++;

        Log.d("Mines Count", ""+count);
        return count;
    }
}

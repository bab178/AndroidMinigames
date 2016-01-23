package com.example.blake.androidminigames;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class TicTacToe extends AppCompatActivity implements View.OnClickListener{

    private static TextView result;
    private static ImageButton one, two, three, four, five, six, seven, eight, nine;
    private static Tile board[] = new Tile[9];
    private static int turn;
    private static int num_turns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttt);

        turn = Math.random() < 0.5 ? 1 : 0;
        num_turns = 0;

        one = (ImageButton) findViewById(R.id.imageButton);
        two = (ImageButton) findViewById(R.id.imageButton2);
        three = (ImageButton) findViewById(R.id.imageButton3);
        four = (ImageButton) findViewById(R.id.imageButton4);
        five = (ImageButton) findViewById(R.id.imageButton5);
        six = (ImageButton) findViewById(R.id.imageButton6);
        seven = (ImageButton) findViewById(R.id.imageButton7);
        eight = (ImageButton) findViewById(R.id.imageButton8);
        nine = (ImageButton) findViewById(R.id.imageButton9);

        result = (TextView) findViewById(R.id.ttt_out);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);

        if(turn == 0)
            result.setText("O's Turn");
        else
            result.setText("X's Turn");

        board[0] = new Tile(one);
        board[1] = new Tile(two);
        board[2] = new Tile(three);
        board[3] = new Tile(four);
        board[4] = new Tile(five);
        board[5] = new Tile(six);
        board[6] = new Tile(seven);
        board[7] = new Tile(eight);
        board[8] = new Tile(nine);
    }

    @Override
    public void onClick(View v) {
        int error = 0;
        switch(v.getId()) {
            case R.id.imageButton:
                if(board[0].state == -1) {
                    board[0].state = turn;
                    one.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else error = 1;
                break;
            case R.id.imageButton2:
                if(board[1].state == -1) {
                    board[1].state = turn;
                    two.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else error = 1;
                break;
            case R.id.imageButton3:
                if(board[2].state == -1) {
                    board[2].state = turn;
                    three.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else error = 1;
                break;
            case R.id.imageButton4:
                if(board[3].state == -1) {
                    board[3].state = turn;
                    four.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else error = 1;
                break;
            case R.id.imageButton5:
                if(board[4].state == -1) {
                    board[4].state = turn;
                    five.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else error = 1;
                break;
            case R.id.imageButton6:
                if(board[5].state == -1) {
                    board[5].state = turn;
                    six.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else error = 1;
                break;
            case R.id.imageButton7:
                if(board[6].state == -1) {
                    board[6].state = turn;
                    seven.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else error = 1;
                break;
            case R.id.imageButton8:
                if(board[7].state == -1) {
                    board[7].state = turn;
                    eight.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else error = 1;
                break;
            case R.id.imageButton9:
                if(board[8].state == -1) {
                    board[8].state = turn;
                    nine.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else error = 1;
                break;
        }
        if(error == 0) {
            if (turn == 0)
                turn = 1;
            else
                turn = 0;

            check_victory(++num_turns, board);
        }
    }

    private class Tile {
        ImageButton ib;
        int state;
        Tile(ImageButton ib) {
            this.ib = ib;
            this.state = -1;
        }
    }

    private boolean check_victory(int num_turns, Tile board[]) {
        //WINS
        if (num_turns > 2) {
            //Horizontals
            if (board[0].state == board[1].state && board[1].state == board[2].state && board[2].state != -1){
            result.setText(getPlayer(board[0].state) + " WINS");
            return true;
            }
            if (board[3].state == board[4].state && board[4].state == board[5].state && board[5].state != -1){
            result.setText(getPlayer(board[3].state) + " WINS");
            return true;
            }
            if (board[6].state == board[7].state && board[7].state == board[8].state && board[8].state != -1){
            result.setText(getPlayer(board[6].state) + " WINS");
            return true;
            }
            //Verticals
            if (board[0].state == board[3].state && board[3].state == board[6].state && board[6].state != -1){
            result.setText(getPlayer(board[0].state) + " WINS");
            return true;
            }
            if (board[1].state == board[4].state && board[4].state == board[7].state && board[7].state != -1){
            result.setText(getPlayer(board[1].state) + " WINS");
            return true;
            }
            if (board[2].state == board[5].state && board[5].state == board[8].state && board[8].state != -1){
            result.setText(getPlayer(board[2].state) + " WINS");
            return true;
            }
            //Diagonals
            if (board[0].state == board[4].state && board[4].state == board[8].state && board[8].state != -1){
            result.setText(getPlayer(board[0].state) + " WINS");
            return true;
            }
            if (board[6].state == board[4].state && board[4].state == board[2].state && board[2].state != -1){
            result.setText(getPlayer(board[6].state) + " WINS");
            return true;
            }
        }
        //DRAW
        if (num_turns == 9){
        result.setText("DRAW");
        return true;
        }

        //No wins or draw
        if(turn == 0)
            result.setText("O's Turn");
        else
            result.setText("X's Turn");
        return false;
    }

    private String getPlayer(int turn) {
        if(turn == 0) return "O";
        else return "X";
    }

    private int getIcon(int turn) {
        if(turn == 0) return android.R.drawable.ic_menu_compass;
        else return android.R.drawable.ic_delete;
    }
}

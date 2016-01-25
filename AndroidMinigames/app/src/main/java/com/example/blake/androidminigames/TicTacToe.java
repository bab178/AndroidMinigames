package com.example.blake.androidminigames;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class TicTacToe extends Activity implements View.OnClickListener{

    private static TextView result;
    private static ImageButton one, two, three, four, five, six, seven, eight, nine, reset;
    private static Tile board[] = new Tile[9];
    private static int turn;
    private static int num_turns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttt);

        //Identify buttons
        one = (ImageButton) findViewById(R.id.imageButton);
        two = (ImageButton) findViewById(R.id.imageButton2);
        three = (ImageButton) findViewById(R.id.imageButton3);
        four = (ImageButton) findViewById(R.id.imageButton4);
        five = (ImageButton) findViewById(R.id.imageButton5);
        six = (ImageButton) findViewById(R.id.imageButton6);
        seven = (ImageButton) findViewById(R.id.imageButton7);
        eight = (ImageButton) findViewById(R.id.imageButton8);
        nine = (ImageButton) findViewById(R.id.imageButton9);
        reset = (ImageButton) findViewById(R.id.reset_btn);

        result = (TextView) findViewById(R.id.ttt_out);

        //Set onClickListeners
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        reset.setOnClickListener(this);

        //Add Tiles to board
        board[0] = new Tile(one);
        board[1] = new Tile(two);
        board[2] = new Tile(three);
        board[3] = new Tile(four);
        board[4] = new Tile(five);
        board[5] = new Tile(six);
        board[6] = new Tile(seven);
        board[7] = new Tile(eight);
        board[8] = new Tile(nine);

        //Change button color
        reset.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);

        //Reset board
        newGame();
    }

    @Override
    public void onClick(View v) {
        int used = 0;
        //Moves
        switch(v.getId()) {
            case R.id.imageButton:
                if(board[0].state == -1) {
                    board[0].state = turn;
                    one.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else used = 1;
                break;
            case R.id.imageButton2:
                if(board[1].state == -1) {
                    board[1].state = turn;
                    two.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else used = 1;
                break;
            case R.id.imageButton3:
                if(board[2].state == -1) {
                    board[2].state = turn;
                    three.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else used = 1;
                break;
            case R.id.imageButton4:
                if(board[3].state == -1) {
                    board[3].state = turn;
                    four.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else used = 1;
                break;
            case R.id.imageButton5:
                if(board[4].state == -1) {
                    board[4].state = turn;
                    five.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else used = 1;
                break;
            case R.id.imageButton6:
                if(board[5].state == -1) {
                    board[5].state = turn;
                    six.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else used = 1;
                break;
            case R.id.imageButton7:
                if(board[6].state == -1) {
                    board[6].state = turn;
                    seven.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else used = 1;
                break;
            case R.id.imageButton8:
                if(board[7].state == -1) {
                    board[7].state = turn;
                    eight.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else used = 1;
                break;
            case R.id.imageButton9:
                if(board[8].state == -1) {
                    board[8].state = turn;
                    nine.setImageDrawable(getResources().getDrawable((getIcon(turn))));
                }
                else used = 1;
                break;
            case R.id.reset_btn:
                newGame();
                break;
        }
        if(used == 0) {
            if (turn == 0)
                turn = 1;
            else
                turn = 0;

            num_turns++;

            check_victory(num_turns, board);
        }
    }

    private boolean check_victory(int num_turns, Tile board[]) {
        //WINS
        if (num_turns > 2) {
            //Horizontals
            if (board[0].state == board[1].state && board[1].state == board[2].state && board[2].state != -1){
                board[0].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[1].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[2].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                result.setText(getPlayer(board[0].state) + " WINS");
                reset.setVisibility(View.VISIBLE);
                return true;
            }
            if (board[3].state == board[4].state && board[4].state == board[5].state && board[5].state != -1){
                board[3].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[4].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[5].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                result.setText(getPlayer(board[3].state) + " WINS");
                reset.setVisibility(View.VISIBLE);
                return true;
            }
            if (board[6].state == board[7].state && board[7].state == board[8].state && board[8].state != -1){
                board[6].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[7].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[8].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                result.setText(getPlayer(board[6].state) + " WINS");
                reset.setVisibility(View.VISIBLE);
                return true;
            }
            //Verticals
            if (board[0].state == board[3].state && board[3].state == board[6].state && board[6].state != -1){
                board[0].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[3].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[6].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                result.setText(getPlayer(board[0].state) + " WINS");
                reset.setVisibility(View.VISIBLE);
                return true;
            }
            if (board[1].state == board[4].state && board[4].state == board[7].state && board[7].state != -1){
                board[1].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[4].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[7].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                result.setText(getPlayer(board[1].state) + " WINS");
                reset.setVisibility(View.VISIBLE);
                return true;
            }
            if (board[2].state == board[5].state && board[5].state == board[8].state && board[8].state != -1){
                board[2].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[5].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[8].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                result.setText(getPlayer(board[2].state) + " WINS");
                reset.setVisibility(View.VISIBLE);
                return true;
            }
            //Diagonals
            if (board[0].state == board[4].state && board[4].state == board[8].state && board[8].state != -1){
                board[0].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[4].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[8].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                result.setText(getPlayer(board[0].state) + " WINS");
                reset.setVisibility(View.VISIBLE);
                return true;
            }
            if (board[6].state == board[4].state && board[4].state == board[2].state && board[2].state != -1){
                board[6].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[4].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                board[2].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                result.setText(getPlayer(board[6].state) + " WINS");
                reset.setVisibility(View.VISIBLE);
                return true;
            }
        }
        //DRAW
        if (num_turns == 9){
            result.setText("DRAW");
            reset.setVisibility(View.VISIBLE);
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

    private void newGame() {
        for(int i = 0; i < 9; i++) {
            board[i].state = -1;
            board[i].ib.setImageResource(android.R.color.transparent);
            board[i].ib.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);
        }

        turn = Math.random() < 0.5 ? 1 : 0;
        num_turns = 0;

        if(turn == 0)
            result.setText("O's Turn");
        else
            result.setText("X's Turn");
        reset.setVisibility(View.INVISIBLE);
    }
}

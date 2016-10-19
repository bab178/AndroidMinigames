package com.example.blake.androidminigames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[]{
                "Tic-Tac-Toe",
                "Minesweeper",
                "Game of Life",
                "Peg Hopper"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.tile, R.id.tile_text, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                switch (itemPosition) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, TicTacToe.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, Minesweeper.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, GameOfLife.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, PegGame.class));
                        break;
                }

            }

        });
    }
}

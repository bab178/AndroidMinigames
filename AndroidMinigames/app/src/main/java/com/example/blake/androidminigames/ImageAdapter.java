package com.example.blake.androidminigames;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private ImageView imageView;
    private Integer[][] board;
    int rowPosition, columnPosition, count;

    public ImageAdapter(Context c, int[][] content) {
        context = c;
        count = 0;
        board = new Integer[content.length][content[0].length];

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                board[i][j] = R.drawable.ms_empty;
                count++;
            }
        }
        rowPosition = 0;
        columnPosition = 0;
    }

    public int getCount() {
        return count;
    }

    public int getRowCount() {
        return board.length;
    }

    public int getColumnCount() {
        return board[0].length;
    }

    public Object getItem(int rowNum, int columnNum) {
        return board[rowNum][columnNum];
    }

    public void setTile(int rowPosition, int columnPosition, int state, View view, ViewGroup viewGroup) {
        board[rowPosition][columnPosition] = state;
        getView(rowPosition, view, viewGroup);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(220, 220));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(2, 2, 2, 2);
        }
        else {
            imageView = (ImageView) convertView;
        }

        columnPosition = position / board[0].length;
        rowPosition = (position - columnPosition) % board[0].length;

        imageView.setImageResource(board[columnPosition][rowPosition]);

        return imageView;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

}
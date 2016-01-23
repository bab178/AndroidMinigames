package com.example.blake.androidminigames;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private static int gridSize = 24;
    private Integer[] mThumbIds;
    ImageView imageView;


    public ImageAdapter(Context c) {
        mContext = c;
        gridSize = 24;
        mThumbIds = mapMaker();
    }

    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return mThumbIds[position];
    }

    public void setTile(int position, int state, View view, ViewGroup viewGroup) {
        mThumbIds[position] = state;
        getView(position, view, viewGroup);
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    private Integer[] mapMaker() {
        Integer[] newGrid = new Integer[gridSize];
        for(int i = 0 ; i < gridSize; i++){
            newGrid[i] = R.drawable.ms_empty;
        }
        return newGrid;
    }
}
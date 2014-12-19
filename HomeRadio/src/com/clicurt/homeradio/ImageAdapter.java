package com.clicurt.homeradio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


/**
 * Created by Blow on 12/15/2014.
 */
@SuppressLint("ViewTag")
public class ImageAdapter extends BaseAdapter {
   private Context mContext;
   private LayoutInflater inflater;
   
    // Keep all Images in array

    public Integer[] mThumbIds = {
            R.drawable.radio_icon, R.drawable.favourite,
            R.drawable.music_icon, R.drawable.twitter_bird ,
            R.drawable.folder, R.drawable.cart_icon
    };
    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
        inflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(
                (int)mContext.getResources().getDimension(R.dimen.width),
                (int)mContext.getResources().getDimension(R.dimen.height)));
        return imageView;
    }
    /*
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        ImageView picture;
        if (v == null) {
            v = inflater.inflate(R.layout.mlroundedimageview, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setLayoutParams(new GridView.LayoutParams(
                    (int)mContext.getResources().getDimension(R.dimen.width),
                    (int)mContext.getResources().getDimension(R.dimen.height)));
        }
        picture = (ImageView) v.getTag(R.id.picture);
        picture.setImageResource(mThumbIds[i]);
        return v;
    }*/
}

package com.clicurt.homeradio;

import android.annotation.SuppressLint;
import android.content.Context;
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
    
    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
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
    	ImageView imageView;
    	if (convertView == null){    		
    		imageView= new ImageView(mContext);
            imageView.setImageResource(mThumbIds[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new GridView.LayoutParams(
                    (int)mContext.getResources().getDimension(R.dimen.width),
                    (int)mContext.getResources().getDimension(R.dimen.height)));
            imageView.setPadding(10, 10, 10, 10);
    	} else {
    		imageView = (ImageView)convertView;
    	}
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
    
    // references to images
    public Integer[] mThumbIds = {
            R.drawable.radio_icon, R.drawable.favourite,
            R.drawable.music_icon, R.drawable.twitter_bird ,
            R.drawable.folder, R.drawable.cart_icon
    };
}
